/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generacion_grafos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author darky
 */
public class Grafo {
  
  
    void ErdosRenyi(int nodos,int aristas){
        ArrayList<Vertice> ListaAdj = new ArrayList(); 
        int i = 0;
        for (int j=0;j<nodos;j++){
          Vertice nodo = new Vertice(Integer.toString(j));
          ListaAdj.add(nodo);
         }
        while ((i < aristas) && (i<nodos*(nodos-1))){
            Random rand = new Random();
            int n1 = rand.nextInt(nodos);
            int n2 = rand.nextInt(nodos);
            if (n1 != n2) {
                System.out.println(i);
             Vertice nodo1,nodo2;
             int index1 = findIndex(ListaAdj,Integer.toString(n1));
             if (index1 < 0){
               nodo1 = new Vertice(Integer.toString(n1));
               ListaAdj.add(nodo1);
               index1 = ListaAdj.size()-1;
              }
             int index2 = findIndex(ListaAdj,Integer.toString(n2));
             if (index2 < 0){
               nodo2 = new Vertice(Integer.toString(n2));
               ListaAdj.add(nodo2);
               index2 = ListaAdj.size()-1;
              }
             nodo1 = ListaAdj.get(index1);
             nodo2 = ListaAdj.get(index2);
             int existente = nodo1.encontrarNodo(Integer.toString(n2));
             if (existente == 0) {
              nodo1.addConections(nodo2);
              nodo2.addConections(nodo1);
              i = i+1;
             }
            } 
        }
        System.out.println("Grafo ErdosRenyi");
        guardarMatriz(ListaAdj,"ErdosRenyi.dot");
        ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
        ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
        Vertice nodo0 = ListaAdj.get(0);
        ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
        guardarMatriz(DFS_RList,"DFS_Recursivo_ErdosRenyi.dot");
        
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_ErdosRenyi.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_ErdosRenyi.dot");
    }
    
    //Método de gilbert, n nodos con probabilidad p de unirse.
    void Gilbert(int nodos,double probabilidad){
        ArrayList<Vertice> ListaAdj = new ArrayList(); 
        Vertice nodo;
        Vertice nodo1,nodo2;
        
        for (int i = 0;i<nodos;i++){ //Agregamos los n nodos a la lista de adjacencia
            nodo = new Vertice(Integer.toString(i));
            ListaAdj.add(nodo);
        }
        
        for (int i=0;i<nodos;i++){
            nodo1 = ListaAdj.get(i);
            for (int j=0;j<nodos;j++){
                nodo2 = ListaAdj.get(j);
                double proba = Math.random();
                int existente = nodo1.encontrarNodo(Integer.toString(j));
                if ((proba < probabilidad) && (i != j) && (existente == 0)){
                    nodo1.addConections(nodo2);
                    nodo2.addConections(nodo1);
                }
            }
        }
        System.out.println("Grafo Gilbert");
        //imprimirLista(ListaAdj);
        guardarMatriz(ListaAdj,"Gilbert.dot");
        ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
        ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
        
        Vertice nodo0 = ListaAdj.get(0);
        ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
        guardarMatriz(DFS_RList,"DFS_Recursivo_Gilbert.dot");
        
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_Gilbert.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_Gilbert.dot");
    }
    
    void Geografico(int nodos,double r){
        ArrayList<Vertice> ListaAdj = new ArrayList(); 
        Vertice nodo;
        Vertice nodo1,nodo2;
        double distancia;
        double coordenadas[][] = new double[nodos][2]; //Creamos una matriz de coordenadas asociada a cada nodo
        Random random = new Random();
        
        for (int j=0;j<nodos;j++){
          Double x = random.nextGaussian();
          Double y = random.nextGaussian();
          coordenadas[j][0]=x;
          coordenadas[j][1]=y;
        }
        
        for (int i = 0;i<nodos;i++){ //Agregamos los n nodos a la lista de adjacencia
            nodo = new Vertice(Integer.toString(i));
            ListaAdj.add(nodo);
        }
        
         for (int i=0;i<nodos;i++){
          nodo1 = ListaAdj.get(i);
          for (int j=0;j<nodos;j++){
              nodo2 = ListaAdj.get(j);
              double x1 = coordenadas[i][0];
              double y1 = coordenadas[i][1];
              double x2 = coordenadas[j][0];
              double y2 = coordenadas[j][1];
              distancia = Math.sqrt(Math.pow(x1-x2,2)+ Math.pow(y1-y2,2));
              int existente = nodo1.encontrarNodo(Integer.toString(j));
              if ((distancia < r) && (i != j) && (existente == 0)) {
                 nodo1.addConections(nodo2);
                 nodo2.addConections(nodo1);
              }
         }  
       }
       System.out.println("Grafo Geográfico");
       //imprimirLista(ListaAdj); 
       guardarMatriz(ListaAdj,"Geografico.dot");
       ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
       ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
       Vertice nodo0 = ListaAdj.get(0);
       ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
       guardarMatriz(DFS_RList,"DFS_Recursivo_Geografico.dot");
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_Geografico.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_Geografico.dot");


    }
    
    void BarabasiAlbert(int nodos,int d){
        ArrayList<Vertice> ListaAdj = new ArrayList();
        Vertice nodo = new Vertice(Integer.toString(0));
        ListaAdj.add(nodo);
        double p,proba;
        int grado;
        Vertice nodo1,nodo2;
        for (int i=1;i<nodos;i++){
            nodo2 = new Vertice(Integer.toString(i));
            for (int j=0;j<i;j++){
                nodo1 = ListaAdj.get(j);
                proba = Math.random(); //generamos un número aleatorio para comparar con p
                grado = nodo1.getConections().size();
                float div = (float)grado/d;
                p = 1 - div;
                if((p >= proba) && (i != j)){
                 nodo1.addConections(nodo2);
                 nodo2.addConections(nodo1);   
                }
            }
            ListaAdj.add(nodo2);
        }
        System.out.println("BarabásiAlbert");
        guardarMatriz(ListaAdj,"Barabasi.dot");
        ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
        ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
        
        Vertice nodo0 = ListaAdj.get(0);
        ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
        guardarMatriz(DFS_RList,"DFS_Recursivo_Barabasi.dot");
        
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_Barabasi.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_Barabasi.dot");
    }
    
  ArrayList<Vertice> DFS_Recursivo(Vertice nodo_inicial){
      ArrayList<Vertice> ListaAdj = new ArrayList();
      ArrayList<Vertice> Lista2 = new ArrayList();
      //Lista2 = DFS_Recursivo_aux(nodo_inicial,ListaAdj);
      DFS_Recursivo_aux(nodo_inicial,ListaAdj);
      return ListaAdj;
  }

void DFS_Recursivo_aux(Vertice nodo_inicial,ArrayList<Vertice> ListaAdj){
//  ArrayList<Vertice> DFS_Recursivo_aux(Vertice nodo_inicial,ArrayList<Vertice> ListaAdj){
        nodo_inicial.addRevisado(1);
        ArrayList<Vertice> nivel_actual = nodo_inicial.getConections();
        Vertice nodo1,nodo2;
        for (int i=0;i<nivel_actual.size();i++){
            Vertice nodo = nivel_actual.get(i);
            if (nodo.getRevisado() == 0){
                int index = findIndex(ListaAdj,nodo_inicial.getLabel());
                if(index<0){
                    nodo1 = new Vertice(nodo_inicial.getLabel());
                    ListaAdj.add(nodo1);
                }
                else {
                    nodo1 = ListaAdj.get(index);
                }
                index = findIndex(ListaAdj,nodo.getLabel());
                if(index<0){
                    nodo2 = new Vertice(nodo.getLabel());
                    ListaAdj.add(nodo2);
                 //   System.out.println("Agegando nodo " + nodo2.getLabel());
                }
                else {
                    nodo2 = ListaAdj.get(index);
                }
                nodo1.addConections(nodo2);
                nodo2.addConections(nodo1); 
                DFS_Recursivo_aux(nodo,ListaAdj);
            }
            //return DFS_Recursivo_aux(nodo,ListaAdj);
        }
        //return ListaAdj;
      //  return 0;
   }
    
    void DFS_Iterativo(ArrayList<Vertice> Lista,Vertice nodo_inicial,ArrayList<Vertice> ListaAdj){
        //ArrayList<Vertice> Lista2 = new ArrayList();
        nodo_inicial.addRevisado(1);
         String nombre = "1",nombre_antiguo = "2";
         int terminar = 0;
         Vertice nodo1,nodo2;
         //System.out.println("Nodo inicial " + nodo_inicial.getLabel());
         while (terminar < Lista.size()){
           nombre_antiguo = nombre;
           ArrayList<Vertice> nivel_actual = nodo_inicial.getConections();
           for (int i=0;i<nivel_actual.size();i++){
             Vertice nodo = nivel_actual.get(i);
             //System.out.println("Revisado " + nodo.getRevisado());
             if (nodo.getRevisado() == 0){
                 //nodo.addRevisado(1);
                 int index = findIndex(ListaAdj,nodo_inicial.getLabel());
                 if(index<0){
                    nodo1 = new Vertice(nodo_inicial.getLabel());
                    ListaAdj.add(nodo1);
                 }
                else {
                    nodo1 = ListaAdj.get(index);
                }
                index = findIndex(ListaAdj,nodo.getLabel());
                if(index<0){
                    nodo2 = new Vertice(nodo.getLabel());
                    ListaAdj.add(nodo2);
                }
                else {
                    nodo2 = ListaAdj.get(index);
                }
                nodo1.addConections(nodo2);
                nodo2.addConections(nodo1); 
                 
                 System.out.println("Nodo " + nodo_inicial.getLabel() + " conectado con " + nodo.getLabel());
                 nodo.addRevisado(1);
                 nodo.addAncestro(nodo_inicial);
                 nodo_inicial = nodo;
                 terminar += 1;
                 nombre = nodo_inicial.getLabel();    
                 break;
             }
             if (i==nivel_actual.size()-1){
                 System.out.println("Final de la rama");
                 nodo_inicial = nodo.getAncestro();
                 terminar += 1;
                 System.out.println("Nuevo nodo inicial " + nodo_inicial.getLabel());
             }
           }
        }
        
    }
    
    void BFS_Recursivo(ArrayList<Vertice> Lista,ArrayList<Vertice> AdjList){
        if (Lista.size()>0){
         ArrayList<Vertice> Cola;//nodo_inicial.getConections();
         Vertice nodo = Lista.get(0);
         nodo.addRevisado(1);
         Lista.remove(0);
         Cola = nodo.getConections();
         Vertice nodo1,nodo2;
         for (int i=0;i<nodo.getConections().size();i++){
           Vertice nuevo_nodo = Cola.get(i);
           if (nuevo_nodo.getRevisado() == 0){
              nuevo_nodo.addRevisado(1);
              Lista.add(nuevo_nodo);
              
              int index = findIndex(AdjList,nodo.getLabel());
                 if(index<0){
                    nodo1 = new Vertice(nodo.getLabel());
                    AdjList.add(nodo1);
                 }
                else {
                    nodo1 = AdjList.get(index);
                }
                index = findIndex(AdjList,nuevo_nodo.getLabel());
                if(index<0){
                    nodo2 = new Vertice(nuevo_nodo.getLabel());
                    AdjList.add(nodo2);
                }
                else {
                    nodo2 = AdjList.get(index);
                }
                nodo1.addConections(nodo2);
                nodo2.addConections(nodo1); 
                 
              System.out.println("Nodo " + nodo.getLabel() + " conectado con " + nuevo_nodo.getLabel());
              
           }
        }
         BFS_Recursivo(Lista,AdjList);
      }

    }

     public ArrayList<Vertice> DeepCopy( ArrayList<Vertice> Lista1){
         //System.out.println("Iniciando copia");
         ArrayList<Vertice> Lista2 = new ArrayList();
         ArrayList<Vertice> nivel = new ArrayList();
         // System.out.println("Tamaño de la lista1 " + Lista1.size());
         for (int i=0;i<Lista1.size();i++){
           Vertice nodo = new Vertice(Lista1.get(i).getLabel());
           Lista2.add(nodo);
          }
         //System.out.println("Lista 1");
         for (int i=0;i<Lista1.size();i++){
           Vertice nodo = Lista1.get(i);
           Vertice nodo_aux = Lista2.get(i);
           //System.out.print("Nodo " + nodo.getLabel() + " conectado con ");
           nivel = nodo.getConections();
           for(int j=0;j<nodo.getConections().size();j++){
               Vertice nodo2 = nivel.get(j);
               Vertice nodo2_aux = Lista2.get(Integer.parseInt(nodo2.getLabel()));
            //   System.out.print(" " + nodo2.getLabel());
               nodo_aux.addConections(nodo2_aux);
           }
          // System.out.println(" ");
          }
        // System.out.println("Fin de la lista");
         
         /*System.out.println("Lista 2");
         for (int i=0;i<Lista2.size();i++){
           Vertice nodo = Lista2.get(i);
           System.out.print("Nodo " + nodo.getLabel() + " conectado con ");
           nivel = nodo.getConections();
           for(int j=0;j<nodo.getConections().size();j++){
               Vertice nodo2 = nivel.get(j);
               System.out.print(" " + nodo2.getLabel());
           }
           System.out.println(" ");
          }*/
          return Lista2;
     }
     public void imprimirLista(ArrayList<Vertice> Lista) {
      for (int i = 0;i<Lista.size();i++){
          Vertice nodo = Lista.get(i);
          String etiqueta = nodo.getLabel();
          System.out.println("nodo " + etiqueta );
         //int val = nodo.encontrarNodo(nodo.getConections(), "1");
          System.out.print("Conectado con: ");
          ArrayList<Vertice> conections = nodo.getConections();
          for (int j = 0;j<conections.size();j++){
              System.out.print(conections.get(j).getLabel() + " ");
          }
          System.out.println(" ");
      }
    }
    
    public int findIndex(ArrayList<Vertice> conections,String vertex){
      int index = -1;
          for (int j = 0;j<conections.size();j++){
            Vertice nodo = conections.get(j);
            String nombre = nodo.getLabel();
            if (nombre.equals(vertex)){
                index = j;
            }
          }      
      return index;
  }
    public void guardarMatriz(ArrayList<Vertice> Lista, String nombre){
       File f;
       FileWriter w;
       BufferedWriter bw;
       PrintWriter wr;
       try {
           f = new File(nombre);
           w = new FileWriter(f);
           bw = new BufferedWriter(w);
           wr = new PrintWriter(bw);
           wr.write("graph abstract {\n");
           
         for (int i = 0;i<Lista.size();i++){
            Vertice nodo = Lista.get(i);
            String etiqueta = nodo.getLabel();
            int n1 = Integer.parseInt(etiqueta);
            ArrayList<Vertice> conections = nodo.getConections();
            for (int j=0;j<conections.size();j++){
                String etiqueta2 = conections.get(j).getLabel();
                int n2 = Integer.parseInt(etiqueta2);
                if (n1 < n2){
                 wr.append("nodo_" + etiqueta + "->" + "nodo_" + etiqueta2 + ";\n");
                }
            }
         }

           wr.append("}"); 
           wr.close();
           bw.close();
           
       } catch (IOException e){
           JOptionPane.showMessageDialog(null, "Error");
       }
    }
   
}
