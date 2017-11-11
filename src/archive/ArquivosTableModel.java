/*
 * Classe modelo da tabela de produtos
 */
package archive;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Classe modelo da tabela de produtos
 *
 * @author Rafael Tavares
 */
public class ArquivosTableModel extends AbstractTableModel {
    private final String[] colunas = {"Nome", "Tamanho (em bytes)"};
    private final List<Arquivo> arquivos;
    
    /**
     * Método construtor para adicionar arquivos à tabela
     * @param lista
     */
    public ArquivosTableModel(List<Arquivo> lista){
        if (lista == null) {
            arquivos = new ArrayList();
        }
        else {
            arquivos = lista;
        }
    }
    
    
    /**
     * Retorna o nome da coluna - também conhecido como Header
     * Polimorfismo: Sobrescrita
     * @param col
     * @return Nome da coluna - Header
     */
    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    /**
     * Retorna a quantidade de linhas na lista
     * Polimorfismo: Sobrescrita
     * @return Quantidade de linhas
     */
    @Override
    public int getRowCount() {
        return arquivos.size();
    }

    /**
     * Retorna a quantidade de colunas da lista
     * Polimorfismo: Sobrescrita
     * @return Quantidade de colunas
     */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    /**
     * Retorna o valor contido na célula especificada por linha e coluna
     * Polimorfismo: Sobrescrita
     * @param indiceLinha
     * @param indiceColuna
     * @return O valor contido na célula especificada
     */
    @Override
    public Object getValueAt(int indiceLinha, int indiceColuna) {
        try {
            switch (indiceColuna) {
            case 0: // Código
                return arquivos.get(indiceLinha).getNome();
            case 1: // Código
                return arquivos.get(indiceLinha).getTamanho();
            default:
                throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
}
