package proyecto;

import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;


public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
        
    int TotalEntradas = 7; //Número de entradas externas del perceptrón
    int TotalSalidas = 1; //Número de salidas externas del perceptrón
    int TotalCapas = 4; //Total capas que tendrá el perceptrón
    int[] neuronasporcapa = new int[TotalCapas + 1]; //Los índices iniciarán en 1 en esta implementación
    neuronasporcapa[1] = TotalEntradas; //Entradas externas del perceptrón
    neuronasporcapa[2] = 7; //Capa oculta con 4 neuronas
    neuronasporcapa[3] = 7; //Capa oculta con 4 neuronas
    neuronasporcapa[4] = TotalSalidas; //Capa de salida con 1 neurona
    Perceptron objP = new Perceptron(); 
    objP.Perceptron(TotalEntradas, TotalSalidas, TotalCapas, neuronasporcapa);
    //Lee los datos de un archivo plano
    archivo ar=new archivo();
    int ConjuntoEntradas = 999;
    double[][] entrada = new double[ConjuntoEntradas + 1][];
    entrada=ar.entradas(entrada);
    /*for(int i = 0;i< 1010; i++){
        for(int j = 0;j< 7; j++){
            System.out.println(Arrays.toString(entrada[i][j]));
        }
    }*/
    double[] salidas = new double[ConjuntoEntradas + 1];
    salidas=ar.salidas(salidas);
    /*for(int i = 0;i< 1010; i++){
        System.out.println(Arrays.toString(salidas[i]));
    }*/
   
    
    
    
    






    //Normaliza los valores entre 0 y 1 que es lo que requiere el perceptrón
    double minimoX = entrada[1][0], maximoX = entrada[1][0];
    double minimoY = salidas[0], maximoY = salidas[0];
    for (int i = 0; i <= ConjuntoEntradas; i++) {
        //System.out.println(entrada[i][1]);
        for (int j = 0; j < TotalEntradas; j++) {
            if (entrada[i][j] > maximoX) 
                maximoX = entrada[i][j];
            if (entrada[i][j] < minimoX) 
            minimoX = entrada[i][j];
        }
        if (salidas[i] > maximoY) 
            maximoY = salidas[i];
        if (salidas[i] < minimoY) 
            minimoY = salidas[i];
    }
    
    for (int cont = 1; cont <= ConjuntoEntradas; cont++) {
         for (int j = 0; j < TotalEntradas; j++) {
            entrada[cont][j] = (entrada[cont][j] - minimoX) / (maximoX - minimoX);
         }
            salidas[cont] = (salidas[cont] - minimoY) / (maximoY - minimoY);        
    }

    


    
    //Inicia el proceso de la red neuronal
    double alpha = 0.15; //Factor de aprendizaje
    for (int epoca = 1; epoca <= 8000; epoca++) {
        if (epoca % 4000 == 0) 
            System.out.println("Iteracion: " + epoca);
    //Importante: Se envía el primer conjunto de entradas-salidas, luego el segundo, tercero y cuarto
    //por cada ciclo de entrenamiento.
    for (int entra = 1; entra <= ConjuntoEntradas; entra++) {        
        objP.Procesa(entrada[entra]);    
        objP.Entrena(alpha, entrada[entra], salidas[entra]);
    }

    }
    //Muestra el resultado
    for (int entra = 0; entra <= ConjuntoEntradas; entra++) {
    objP.Procesa(entrada[entra]);
    objP.Muestra(entrada[entra], salidas[entra], minimoX, maximoX, minimoY, maximoY);
    objP.escribir( minimoX, maximoX, minimoY, maximoY);
    }
    objP.guardar_pesos();
    }
    }