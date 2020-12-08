/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generacion_grafos;

import java.util.ArrayList;

/**
 *
 * @author darky
 */
public class Vertice {
    private String label; //Etiqueta del nodo
    private Vertice descendiente; //nodo siguiente
    private Vertice ancestro;  //nodo ancestro
    private ArrayList<Vertice> conexiones = new ArrayList();
    private int revisado = 0;

    public Vertice(String label) {
        this.label = label;
    }
    
    public void addAncestro(Vertice ancestro) {
      this.ancestro = ancestro;
    }
    public void addSiguiente(Vertice descendiente) {
      this.descendiente = descendiente;
    }
    
    public void addRevisado(int revisado) {
      this.revisado = revisado;
    }
 
    public void addConections(Vertice nodo) {
        conexiones.add(nodo);
    }
    
  public String getLabel (){
      return label;
  }  
  public Vertice getAncestro (){
      return ancestro;
  }
  public Vertice getDescendiente (){
      return descendiente;
  }
  public ArrayList<Vertice> getConections (){
      return conexiones;
  }
  
   public int getRevisado() {
      return revisado;
    }
  
  public int encontrarNodo(String vertex){
      int valor = 0;
      for (int j = 0;j<conexiones.size();j++){
            Vertice nodo = conexiones.get(j);
            String nombre = nodo.getLabel();
            if (nombre.equals(vertex)){
                valor = 1;
            }
          }
      return valor;
  }
  /*public int findIndex(ArrayList<Vertice> conections,String vertex){
      int index = -1;
          for (int j = 0;j<conections.size();j++){
            Vertice nodo = conections.get(j);
            String nombre = nodo.getLabel();
            if (nombre.equals(vertex)){
                index = j;
            }
          }      
      return index;
  }*/
}
