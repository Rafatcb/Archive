/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archive;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class Arquivador {
    private static final int ESPACO_HEADER = 11684; // 2 + (10+8+100)*99
    private static final int ESPACO_ARQUIVO_HEADER = 118; // 10+8+100
    private int quantidadeArquivos;
    private List<Arquivo> arquivos;
    private File archive;
    private File extracao;


    /**
     * Salvar a atual lista de arquivos em memória para o archive especificado
     */
    private void salvar() {
        try {
            if (this.quantidadeArquivos > 0) {
                RandomAccessFile raf = new RandomAccessFile(this.archive, "rw");
                Arquivador archiveTemp = new Arquivador(this.archive);
                for (int i = 0; i < this.quantidadeArquivos; i++) {
                    /* Gravação das novas informações no Header */
                    String qtd = String.format("%02d", this.quantidadeArquivos);
                    raf.seek(0);
                    raf.write(qtd.getBytes());

                    raf.seek(2 + i*ESPACO_ARQUIVO_HEADER);
                    String pos = String.format("%010d", this.arquivos.get(i).getPosicaoInicio());
                    raf.write(pos.getBytes());
                    String tam = String.format("%08d", this.arquivos.get(i).getTamanho());
                    raf.write(tam.getBytes());
                    String nome = preencherDireita(this.arquivos.get(i).getNome(), 100);
                    raf.write(nome.getBytes());

                    /* Gravação do Arquivo no Archive */
                    raf.seek(this.arquivos.get(i).getPosicaoInicio());
                    raf.write(archiveTemp.getBytesArquivo(this.arquivos.get(i).getNome()));
                }
                /* Preencher Header com espaços */
                raf.seek(2 + this.quantidadeArquivos*ESPACO_ARQUIVO_HEADER);
                String preencher = "";
                preencher = preencherDireita(preencher, ESPACO_HEADER - 2 - this.quantidadeArquivos*ESPACO_ARQUIVO_HEADER);
                raf.write(preencher.getBytes());
                raf.close();
            }
            else {
                Files.delete(this.archive.toPath());
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Ler todo o header do archive especificado para montar a lista de arquivos em memória
     */
    private void lerMontarLista() {
        this.arquivos = new ArrayList();
        try{
            byte[] buffer = new byte[2];
            InputStream is = new FileInputStream(this.archive);
            is.read(buffer);
            this.quantidadeArquivos = (Character.getNumericValue(buffer[0])*10 + Character.getNumericValue(buffer[1])); // ASCII -> número
            if (this.quantidadeArquivos > 0) {
                byte[] bufferPosInicio = new byte[10];
                byte[] bufferTamanho = new byte[8];
                byte[] bufferNome = new byte[100];
                for (int i = 0; i < quantidadeArquivos; i++) {
                    is.read(bufferPosInicio);
                    is.read(bufferTamanho);
                    is.read(bufferNome);

                    Arquivo arq = new Arquivo();
                    arq.setPosicaoInicio(converterPosInicio(bufferPosInicio));
                    arq.setTamanho(converterTamanho(bufferTamanho));
                    arq.setNome(new String(bufferNome).replaceAll("\\s+$", ""));
                    this.arquivos.add(arq);
                }
            }
            is.close();
        } catch (NullPointerException | IOException ex) {
        }
    }
    
    /**
     * Adiciona o arquivo recebido como parâmetro no archive
     * @param arquivo
     * @return True se adicionado
     */
    public boolean escreverArquivo(File arquivo) {
        try {
            /* Leitura do Arquivo para um buffer */
            FileInputStream fileInput = new FileInputStream(arquivo);
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int readNum;
            while ((readNum = fileInput.read(buf)) != -1) { 
                byteOutput.write(buf, 0, readNum);
            } 
            byte[] bytes = byteOutput.toByteArray();
            byteOutput.close();
            fileInput.close();

            
            try {
                Arquivo a = new Arquivo();
                a.setNome(arquivo.getName());
                a.setTamanho(byteOutput.size());
                a.setPosicaoInicio(this.posicaoInicialProxArq());

                RandomAccessFile raf = new RandomAccessFile(archive, "rw");

                /* Gravação das novas informações no Header */
                String qtd = String.format("%02d", this.quantidadeArquivos+1);
                raf.seek(0);
                raf.write(qtd.getBytes());

                raf.seek(2 + this.quantidadeArquivos*ESPACO_ARQUIVO_HEADER);
                String pos = String.format("%010d", a.getPosicaoInicio());
                raf.write(pos.getBytes());
                String tam = String.format("%08d", a.getTamanho());
                raf.write(tam.getBytes());
                String nome = preencherDireita(a.getNome(), 100);
                raf.write(nome.getBytes());
                String preencher = "";
                preencher = preencherDireita(preencher, ESPACO_HEADER - 2 - (quantidadeArquivos+1)*ESPACO_ARQUIVO_HEADER);
                raf.write(preencher.getBytes());

                /* Gravação do Arquivo no Archive */
                raf.seek(a.getPosicaoInicio());
                raf.write(bytes);
                
                raf.close();

                this.addArquivos(a);
                return true;
            } catch (IOException ex) {
            }
        } catch (IOException ex) {
        }
        if (arquivos.isEmpty()) {
            try {
                Files.delete(archive.toPath());
                return true;
            } catch (IOException ex) {
            }
        }
        return false;
    }
    
    /**
     * Método para extrair um arquivo para o mesmo diretório em que o archive está
     * @param pos - Posição do arquivo na tabela, começando do 0
     */
    public void extrairArquivo(int pos) {
        try {
            RandomAccessFile raf = new RandomAccessFile(this.archive, "r");

            /* Leitura do Arquivo */
            raf.seek(arquivos.get(pos).getPosicaoInicio());
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            byte[] bytes;
            long faltam = arquivos.get(pos).getTamanho();
            while (faltam > 4096) {
                faltam -= 4096;
                byteOutput.write(buf, 0, raf.read(buf));
            } 
            byteOutput.write(buf, 0, raf.read(buf, 0, (int) faltam));
            bytes = byteOutput.toByteArray();
            byteOutput.close();
            raf.close();
            
            /* Escrita do arquivo */
            File futuroArquivo = new File(this.extracao + File.separator + this.arquivos.get(pos).getNome());
            FileOutputStream fos = new FileOutputStream(futuroArquivo);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException ex) {
        }
    }
    
    private byte[] getBytesArquivo(String nome) {
        try {
            RandomAccessFile raf = new RandomAccessFile(this.archive, "r");
            int pos = 0;
            /* Chegar ao arquivo desejado */
            while (!arquivos.get(pos).getNome().equals(nome)) {
                pos++;
            }
            
            /* Leitura do Arquivo */
            raf.seek(arquivos.get(pos).getPosicaoInicio());
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            byte[] bytes;
            long faltam = arquivos.get(pos).getTamanho();
            while (faltam > 4096) {
                faltam -= 4096;
                byteOutput.write(buf, 0, raf.read(buf));
            } 
            byteOutput.write(buf, 0, raf.read(buf, 0, (int) faltam));
            bytes = byteOutput.toByteArray();
            byteOutput.close();
            raf.close();
            
            /* Retorno do arquivo */
            return bytes;
        } catch (IOException ex) {
            return null;
        }
    }
    
    /**
     * Converte os 10 bytes da 'posição inicio' para um long
     * @param pos
     * @return Posição long
     */
    private long converterPosInicio(byte[] pos) {
        long resultado = Character.getNumericValue(pos[0])*1000000000 + Character.getNumericValue(pos[1])*100000000 + 
                Character.getNumericValue(pos[2])*10000000 + Character.getNumericValue(pos[3])*1000000 + 
                Character.getNumericValue(pos[4])*100000 + Character.getNumericValue(pos[5])*10000 + 
                Character.getNumericValue(pos[6])*1000 + Character.getNumericValue(pos[7])*100 + 
                Character.getNumericValue(pos[8])*10 + Character.getNumericValue(pos[9]);
        return resultado;
    }
    
    /**
     * Converte os 8 bytes do 'tamanho' para um long
     * @param tam
     * @return Tamanho long
     */
    private long converterTamanho(byte[] tam) {
        long resultado = Character.getNumericValue(tam[0])*10000000 + Character.getNumericValue(tam[1])*1000000 + 
                Character.getNumericValue(tam[2])*100000 + Character.getNumericValue(tam[3])*10000 + 
                Character.getNumericValue(tam[4])*1000 + Character.getNumericValue(tam[5])*100 + 
                Character.getNumericValue(tam[6])*10 + Character.getNumericValue(tam[7]);
        return resultado;
    }
    
    /**
     * Preenche a String do lado direito com espaço n vezes
     * @param s - string a ser preenchida
     * @param n - quantidade de espaços
     * @return String preenchida
     */
    private String preencherDireita(String s, int n) {
        return String.format("%1$-" + n + "s", s);  
    }
    
    /**
     * Retorna a posição inicial do ultimo arquivo a ser inserido
     * @return Posicao Inicial do último arquivo inserido
     */
    public long posicaoInicialProxArq() {
        long posicao = ESPACO_HEADER;
        if (this.quantidadeArquivos > 0) {
            for (int i = 0; i < quantidadeArquivos; i++) {
                posicao += arquivos.get(i).getTamanho();
            }
        }
        return posicao;
    }
    
    public Arquivador(File archive) {
        this.archive = archive;
        lerMontarLista();
    }

    public File getExtracao() {
        return extracao;
    }

    public void setExtracao(File extracao) {
        this.extracao = extracao;
    }

    public File getArchive() {
        return archive;
    }

    public void setArchive(File archive) {
        this.archive = archive;
    }
    
    public void addArquivos(Arquivo arq) {
        arquivos.add(arq);
        this.quantidadeArquivos++;
    }
    
    public void removeArquivos(int pos) {
        arquivos.remove(pos);
        this.quantidadeArquivos--;
        this.salvar();
    }
    
    public int getQuantidadeArquivos() {
        return quantidadeArquivos;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }
    
    
}