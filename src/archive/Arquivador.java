/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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


    /**
     * Salvar a atual lista de arquivos em memória para o archive especificado
     * @param archive 
     */
    public void salvar(File archive) {
        if (arquivos.isEmpty()) {
            try {
                Files.delete(archive.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            String qtd = String.format("%02d", this.quantidadeArquivos);
            try {
                OutputStream os = new FileOutputStream(archive);
                PrintWriter pw = new PrintWriter(os);
                pw.write(qtd);
                String pos = "";
                String tam = "";
                String nome = "";
                for (int i = 0; i < this.quantidadeArquivos; i++) {
                    pos = String.format("%010d", this.arquivos.get(i).getPosicaoInicio());
                    pw.write(pos);
                    tam = String.format("%08d", this.arquivos.get(i).getTamanho());
                    pw.append(tam);
                    nome = preencherDireita(this.arquivos.get(i).getNome(), 100);
                    pw.append(nome);
                }
                String preencher = "";
                preencher = preencherDireita(preencher, ESPACO_HEADER - 2 - quantidadeArquivos*ESPACO_ARQUIVO_HEADER);
                pw.append(preencher);
                System.out.println(qtd.length() + " + " + pos.length() + " + " + tam.length() + " + " + nome.length() + " + " + preencher.length());
                pw.close();
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Ler todo o header do archvie especificado para montar a lista de arquivos em memória
     * @param archive 
     */
    public void ler(File archive) {
        try{
            byte[] buffer = new byte[2];
            InputStream is = new FileInputStream(archive);
            is.read(buffer);
            this.quantidadeArquivos = (Character.getNumericValue(buffer[0])*10 + Character.getNumericValue(buffer[1])); // ASCII -> número
            if (this.quantidadeArquivos > 0) {
               byte[] bufferPosInicio = new byte[10];
               byte[] bufferTamanho = new byte[18];
               byte[] bufferNome = new byte[100];
               for (int i = 0; i < quantidadeArquivos; i++) {
                   is.read(bufferPosInicio);
                   is.read(bufferTamanho);
                   is.read(bufferNome);
                   
                   Arquivo arq = new Arquivo();
                   arq.setPosicaoInicio(converterPosInicio(bufferPosInicio));
                   arq.setTamanho(converterTamanho(bufferTamanho));
                   arq.setNome(new String(bufferNome));
               }
            }
            is.close();
        } catch (NullPointerException | IOException ex) {
            // arquivo n existe
            arquivos = new ArrayList();
        }
    }
    
    /**
     * Converte os 10 bytes da 'posição inicio' para um long
     * @param pos
     * @return Posição long
     */
    private long converterPosInicio(byte[] pos) {
        long resultado = pos[0]*1000000000 + pos[1]*100000000 + pos[2]*10000000 + pos[3]*1000000 + pos[4]*100000 + pos[5]*10000 + pos[6]*1000 + pos[7]*100 + pos[8]*10 + pos[9];
        return resultado;
    }
    
    /**
     * Converte os 8 bytes do 'tamanho' para um long
     * @param tam
     * @return Tamanho long
     */
    private long converterTamanho(byte[] tam) {
        long resultado = tam[0]*10000000 + tam[1]*1000000 + tam[2]*100000 + tam[3]*10000 + tam[4]*1000 + tam[5]*100 + tam[6]*10 + tam[7];
        return resultado;
    }
    
    /**
     * Preenche a String do lado direito com espaço n vezes
     * @param s - string a ser preenchida
     * @param n - quantidade de espaços
     * @return String preenchida
     */
    public String preencherDireita(String s, int n) {
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
        ler(archive);
    }
    
    public void addArquivos(Arquivo arq) {
        arq.setPosicaoInicio(posicaoInicialProxArq());
        arquivos.add(arq);
        this.quantidadeArquivos++;
    }
    
    public void removeArquivos(int pos) {
        arquivos.remove(pos);
        this.quantidadeArquivos--;
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
