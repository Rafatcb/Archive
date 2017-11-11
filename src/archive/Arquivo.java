/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archive;

/**
 *
 * @author rafae
 */
public class Arquivo {
    private String nome;
    private int posicaoInicio;
    private long tamanho;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosicaoInicio() {
        return posicaoInicio;
    }

    public void setPosicaoInicio(int posicaoInicio) {
        this.posicaoInicio = posicaoInicio;
    }

    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }
    
    
}
