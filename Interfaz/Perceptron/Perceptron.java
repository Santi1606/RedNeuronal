package red;

import java.io.BufferedReader;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class Perceptron {
        double entrada1;
        double entrada2;
        double entrada3;
        double entrada4;
        double entrada5;
        double entrada6;
        double entrada7;
        ArrayList <Double> entradas =  new ArrayList(); 
        ArrayList <Double> entradas_nor = new ArrayList(); 
        double salida_final;
        double [][][] pesos;//Los pesos serán arreglos multidimensionales. Así: W[capa, neurona inicial, neurona final],
        double [][] umbral; //Los umbrales de cada neurona serán arreglos bidimensionales. Así: U[capa, neurona que produce la salida]
        double [][] salida; //Las salidas de cada neurona serán arreglos bidimensionales. Así: A[capa, neurona que produce la salida] 
        //double[][][] nuevos_pesos;//Los nuevos pesos serán arreglos multidimensionales. Así: W[capa, neurona inicial, neurona final]
        //double[][] nuevos_umbrales; //Los nuevos umbrales de cada neurona serán arreglos bidimensionales. Así: U[capa, neurona que produce la salida]
        int TotalCapas; //El total de capas que tendrá el perceptrón incluyendo la capa de entrada
        int[] neuronasporcapa; //Cuantas neuronas habrá en cada capa
        int TotalEntradas; //Total de entradas externas del perceptrón
        int TotalSalidas; //Total salidas externas del perceptrón
        double minimoX;
        double maximoX;
        double minimoy;
        double maximoy;


        public long ingresar_entradas(){

            entradas.add(entrada1);
            entradas.add(entrada2);
            entradas.add(entrada3);
            entradas.add(entrada4);
            entradas.add(entrada5); 
            entradas.add(entrada6); 
            entradas.add(entrada7);
            Perceptron();
            leer_pesos();


            /*for(int i = 0; i<entradas.size(); i++){
                System.out.print(entradas.get(i));
            }*/

            normalizar();

            procesa();        
            return calcular_resultado();


        }

        public void leer_pesos(){

            BufferedReader acu2;
            try{
                File archivo=new File("C:\\Users\\miran\\OneDrive\\Escritorio\\Trabajos\\Laboratorio\\RED\\src\\red\\pesos.txt");
                FileReader pesos_leidos= new FileReader(archivo);
                acu2=new BufferedReader(pesos_leidos);            

                for (int capa = 2; capa <= TotalCapas; capa++){
                    for (int neurona = 1; neurona <= neuronasporcapa[capa]; neurona++) {
                        for (int entra = 1; entra <= neuronasporcapa[capa - 1]; entra++){                                                             
                            String lectura_pesos= acu2.readLine();                
                            //String vector[]=lectura_pesos.split(",");                        
                            //System.out.println(lectura_pesos);
                            double y;
                            y=Double.parseDouble(lectura_pesos);  
                            pesos[capa][entra][neurona]= y;                        
                        }                         
                    } 
                }
            }
            catch(IOException ex){
                System.err.println("error "+ex.getMessage());                   
            }

        }


        public void Perceptron() {
                this.TotalEntradas = 7;
                this.TotalSalidas = 1;
                this.TotalCapas = 4;
                int maxNeuronas = 0; //Detecta el máximo número de neuronas por capa para dimensionar los arreglos
                this.neuronasporcapa = new int[TotalCapas + 1];
                for (int capa = 1; capa <= TotalCapas; capa++) {
                    this.neuronasporcapa[capa] = neuronasporcapa[capa];
                    if (neuronasporcapa[capa] > maxNeuronas){ 
                        maxNeuronas = neuronasporcapa[capa];
                    }   
                }
                maxNeuronas = 8;
                neuronasporcapa[1] = TotalEntradas; //Entradas externas del perceptrón
                neuronasporcapa[2] = 6; //Capa oculta con 4 neuronas
                neuronasporcapa[3] = 5; //Capa oculta con 4 neuronas
                neuronasporcapa[4] = TotalSalidas; //Capa de salida con 1 neurona


            //Dimensiona con el máximo valor
                pesos = new double[TotalCapas + 1][maxNeuronas + 1][maxNeuronas + 1];

                umbral = new double[TotalCapas + 1][maxNeuronas + 1];



                salida = new double[TotalCapas + 1][maxNeuronas + 1];




                for (int capa = 2; capa <= TotalCapas; capa++){
                    for (int i = 1; i <= neuronasporcapa[capa]; i++){
                        umbral[capa][i] = 1;
                    }
                }


            }

        private void normalizar(){
            //Normaliza los valores entre 0 y 1 que es lo que requiere el perceptrón
            minimoX = entradas.get(0);
            maximoX = entradas.get(0);
                for (int j = 0; j < TotalEntradas; j++) {
                    if (entradas.get(j) > maximoX) 
                        maximoX = entradas.get(j);
                    if (entradas.get(j) < minimoX) 
                    minimoX = entradas.get(j);
                }



             for (int j = 0; j < TotalEntradas; j++) {
                double x=entradas.get(j);
                x =(entradas.get(j) - minimoX) / (maximoX - minimoX);
                entradas_nor.add(x);
             }       
        }



        private void procesa(){
            for (int i = 1; i < TotalCapas; i++){
                //System.out.println("aaa: "+neuronasporcapa[i]);
                for (int j = 1; j <= neuronasporcapa[i-1]; j++){

                    //System.out.println("aaa: "+ salida[i][j]);
                }
            }


            //Entradas externas del perceptrón pasan a la salida de la primera capa
            for (int copia = 1; copia < TotalEntradas; copia++){

                salida[1][copia] = entradas_nor.get(copia);                
            }


            //Proceso del perceptrón
            for (int capa = 2; capa <= TotalCapas; capa++){
                for (int neurona = 1; neurona <= neuronasporcapa[capa]; neurona++) {
                    salida[capa][neurona] = 0;
                    for (int entra = 1; entra <= neuronasporcapa[capa - 1]; entra++){
                        salida[capa][neurona] += salida[capa - 1][entra] * pesos[capa - 1][entra][neurona];                                         
                    }
                    salida[capa][neurona] += umbral[capa][neurona];
                    salida[capa][neurona] = 1 / (1 + Math.exp(-salida[capa][neurona]));                
                    System.out.println("aaa: "+salida[capa][neurona]);
                }
            }
        }




        private long calcular_resultado(){      
            double y=salida[TotalCapas][1]*( 4.0 - 1.0)+1.0; 
            System.out.println("SALIDA FINAL: "+y);
            long x=Math.round(y);
            System.out.println("SALIDA FINAL: "+x);
            return x-50000;
        }

    }