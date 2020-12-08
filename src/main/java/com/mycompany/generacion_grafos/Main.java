/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generacion_grafos;
//https://www.baeldung.com/java-graphs

import java.util.ArrayList;

/**
 *
 * @author darky
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo graph1 = new Grafo();
        graph1.ErdosRenyi(500,6000);
        //Grafo graph2 = new Grafo();
        //graph2.Gilbert(500,0.5);
        //Grafo graph3 = new Grafo();
        //graph3.Geografico(500,0.6);
        //Grafo graph4 = new Grafo();
        //graph4.BarabasiAlbert(500,4);
        
        /*Vertice nodo0 = new Vertice(Integer.toString(0));
        Vertice nodo1 = new Vertice(Integer.toString(1));
        Vertice nodo2 = new Vertice(Integer.toString(2));
        Vertice nodo3 = new Vertice(Integer.toString(3));
        Vertice nodo4 = new Vertice(Integer.toString(4));
        nodo0.addConections(nodo1);
        nodo0.addConections(nodo2);
        nodo1.addConections(nodo0);
        nodo1.addConections(nodo2);
        nodo1.addConections(nodo3);
        nodo2.addConections(nodo0);
        nodo2.addConections(nodo1);
        nodo2.addConections(nodo4);
        nodo3.addConections(nodo1);
        nodo4.addConections(nodo2);
        ArrayList<Vertice> ListaAdj = new ArrayList();
        ListaAdj.add(nodo0);
        ListaAdj.add(nodo1);
        ListaAdj.add(nodo2);
        ListaAdj.add(nodo3);
        ListaAdj.add(nodo4);
//        ArrayList<Vertice> ListaDFS = graph4.DFS_Recursivo(ListaAdj,nodo0);
        //graph4.DFS_Iterativo(ListaAdj,nodo0);
        //graph4.DFS_Recursivo(ListaAdj,nodo0);
        ArrayList<Vertice> Lista = new ArrayList();
        Lista.add(nodo0);
        Lista = graph4.DFS_Recursivo(nodo0);
        graph4.guardarMatriz(Lista,"DFS_Recursivo_Prueba.dot");
//        graph4.BFS_Recursivo(Lista);
//        graph4.guardarMatriz(ListaDFS,"DFS_Recursivo.dot");
        //graph4.BFS_Iterativo(ListaAdj,nodo0);
        //nodo.addRevisado(1);
        //int r = nodo.getRevisado();
        //System.out.println(r);*/
    }
    
}
