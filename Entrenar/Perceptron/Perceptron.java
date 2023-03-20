package proyecto;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Perceptron {
        double [][][] pesos;//Los pesos serán arreglos multidimensionales. Así: W[capa, neurona inicial, neurona final],
        double [][] umbral; //Los umbrales de cada neurona serán arreglos bidimensionales. Así: U[capa, neurona que produce la salida]
        double [][] salida; //Las salidas de cada neurona serán arreglos bidimensionales. Así: A[capa, neurona que produce la salida] 
        double[][][] nuevos_pesos;//Los nuevos pesos serán arreglos multidimensionales. Así: W[capa, neurona inicial, neurona final]
        double[][] nuevos_umbrales; //Los nuevos umbrales de cada neurona serán arreglos bidimensionales. Así: U[capa, neurona que produce la salida]
        int TotalCapas; //El total de capas que tendrá el perceptrón incluyendo la capa de entrada
        int[] neuronasporcapa; //Cuantas neuronas habrá en cada capa
        int TotalEntradas; //Total de entradas externas del perceptrón
        int TotalSalidas; //Total salidas externas del perceptrón

        public void Perceptron(int TotalEntradas, int TotalSalidas, int TotalCapas, int[] neuronasporcapa) {
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

        //Dimensiona con el máximo valor
            pesos = new double[TotalCapas + 1][maxNeuronas + 1][maxNeuronas + 1];
            
            umbral = new double[TotalCapas + 1][maxNeuronas + 1];
            
            nuevos_pesos = new double[TotalCapas + 1][maxNeuronas + 1][maxNeuronas + 1];

                                    
            nuevos_umbrales = new double[TotalCapas + 1][maxNeuronas + 1];

            
            salida = new double[TotalCapas + 1][maxNeuronas + 1];


            //Da valores aleatorios a pesos y umbrales
            Random azar = new Random();

            for (int capa = 2; capa <= TotalCapas; capa++){
                for (int i = 1; i <= neuronasporcapa[capa]; i++){
                    umbral[capa][i] = azar.nextDouble();
                }
            }

            for (int capa = 1; capa < TotalCapas; capa++){
                for (int i = 1; i <= neuronasporcapa[capa]; i++){
                    for (int j = 1; j <= neuronasporcapa[capa+1]; j++){
                        pesos[capa][i][j] = azar.nextDouble();
                    }
                }
            }
        }
//-------------------------------------------------------------------------------
        
        
        public void Procesa(double[] E) {
            //Entradas externas del perceptrón pasan a la salida de la primera capa
            for (int copia = 1; copia < TotalEntradas; copia++){ 
                salida[1][copia] = E[copia];
                
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
                    //corregido
                }
            }
        }
        
//---------------------------------------------------------------------------------------------------------       
        // Muestra las entradas externas del perceptrón, las salidas esperadas y las salidas reales
        public void Muestra(double[] E, double S, double minimoX, double maximoX, double minimoY, double maximoY) {
            
            for(int i = 0;i< 7; i++){
                System.out.print(E[i]*(maximoX- minimoX)+minimoX+" , ");
            }
            //System.out.print(E[1]*(maximoX- minimoX)+minimoX); 
            System.out.print(" > ");
            System.out.print(S*(maximoY- minimoY)+minimoY); 
            System.out.print(" <vs> ");
            System.out.println(salida[TotalCapas][1]*(maximoY- minimoY)+minimoY);
            //Salidas reales del perceptrón
        }

        //El entrenamiento es ajustar los pesos y umbrales
        public void Entrena(double alpha, double[] Entrada, double Salida) {
            //Ajusta pesos capa3 ==> capa4
            for (int j = 1; j <= neuronasporcapa[3]; j++){
                for (int i = 1; i <= neuronasporcapa[4]; i++) {                    
                    double Yi = salida[4][i];
                    
                    double dE3 = salida[3][j] * (Yi - Salida) * Yi * (1 - Yi);
                    nuevos_pesos[3][j][i] = pesos[3][j][i/*0 o 1*/] - alpha * dE3; //Nuevo peso se guarda temporalmente
                    //corregido
                }
            }

            //Ajusta pesos capa2 ==> capa3
            for (int j = 1; j <= neuronasporcapa[2]; j++){
                for (int k = 1; k <= neuronasporcapa[3]; k++){
                    double acum = 0;
                    for (int i = 1; i <= neuronasporcapa[4]; i++) {
                        double Yi = salida[4][i];
                        acum += pesos[3][k][1/*0 o 1*/] * (Yi - Salida) * Yi * (1 - Yi);
                    }
                    double dE2 = salida[2][j] * salida[3][k] * (1 - salida[3][k]) * acum;
                    nuevos_pesos[2][j][k] = pesos[2][j][k] - alpha * dE2; //Nuevo peso se guarda temporalmente
                    //corregido
                }
            }
            //Ajusta pesos capa1 ==> capa2
            for (int j = 1; j < neuronasporcapa[1]; j++){
                for (int k = 1; k <= neuronasporcapa[2]; k++){
                    double acumular = 0;
                    for (int p = 1; p <= neuronasporcapa[3]; p++) {
                        double acum = 0;
                        for (int i = 1; i <= neuronasporcapa[4]; i++) {
                            double Yi = salida[4][i/*0 o 1*/];
                            acum += pesos[3][p][i/*0 o 1*/] * (Yi - Salida) * Yi * (1 - Yi);
                        }
                        acumular += pesos[2][k][p] * salida[3][p] * (1 - salida[3][p]) * acum;
                    }
                    double dE1 = Entrada[j] * salida[2][k] * (1 - salida[2][k]) * acumular;
                    nuevos_pesos[1][j][k] = pesos[1][j][k] - alpha * dE1; //Nuevo peso se guarda temporalmente
                    //corregido
                    
                    
                }
            }

            //Ajusta umbrales de neuronas de la capa 4
            for (int i = 1; i <= neuronasporcapa[4]; i++) {
                double Yi = salida[4][i];
                double dE4 = (Yi - Salida) * Yi * (1 - Yi);
                nuevos_umbrales[4][i] = umbral[4][i/*0 o 1*/] - alpha * dE4; //Nuevo umbral se guarda temporalmente
                //corregido
                
                
            }

            //Ajusta umbrales de neuronas de la capa 3
            for (int k = 1; k <= neuronasporcapa[3]; k++) {
                double acum = 0;
                for (int i = 1; i <= neuronasporcapa[4]; i++) {
                    double Yi = salida[4][i/*0 o 1*/];
                    acum += pesos[3][k][i/*0 o 1*/] * (Yi - Salida) * Yi * (1 - Yi);
                }
                double dE3 = salida[3][k] * (1 - salida[3][k]) * acum;
                nuevos_umbrales[3][k] = umbral[3][k] - alpha * dE3; //Nuevo umbral se guarda temporalmente
                //corregido
            }

            //Ajusta umbrales de neuronas de la capa 2
            for (int k = 1; k <= neuronasporcapa[2]; k++) {
                double acumular = 0;
                for (int p = 1; p <= neuronasporcapa[3]; p++) {
                    double acum = 0;
                    for (int i = 1; i <= neuronasporcapa[4]; i++) {
                        double Yi = salida[4][i/*0 o 1*/];
                        acum += pesos[3][p][i/*0 o 1*/] * (Yi - Salida) * Yi * (1 - Yi);
                    }
                    acumular += pesos[2][k][p] * salida[3][p] * (1 - salida[3][p]) * acum;
                }
                double dE2 = salida[2][k] * (1 - salida[2][k]) * acumular;
                nuevos_umbrales[2][k] = umbral[2][k] - alpha * dE2; //Nuevo umbral se guarda temporalmente  
                //corregido
            }


            //Copia los nuevos pesos y umbrales a los pesos y umbrales respectivos del perceptrón
            for (int capa = 2; capa <= TotalCapas; capa++){
                for (int i = 1; i <= neuronasporcapa[capa]; i++){
                    umbral[capa][i] = nuevos_umbrales[capa][i];
                    //corregido
                    
                }
            }

            for (int capa = 1; capa < TotalCapas; capa++){
                for (int i = 1; i < neuronasporcapa[capa]; i++){
                    for (int j = 1; j <= neuronasporcapa[capa + 1]; j++){
                        pesos[capa][i][j] = nuevos_pesos[capa][i][j]; 
                        //corregido
                    }
                }
            }
        }
        
        
        public void escribir( double minimoX, double maximoX, double minimoY, double maximoY){
            BufferedWriter bw=null;
            FileWriter fw=null;
            try{
                double y=salida[TotalCapas][1]*(maximoY- minimoY)+minimoY;
                String info=String.valueOf(y);
                File datos=new File("C:\\Users\\Santiago Candia\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto\\src\\proyecto\\salidasreales.txt");
                fw=new FileWriter(datos.getAbsoluteFile(),true);
                bw= new BufferedWriter(fw);            
                bw.write(info+"\n");            
                bw.close();
                fw.close();
            }
            catch(IOException e){
                System.err.println("error "+e.getMessage()); 
            }                              
        }
        
        
        public void guardar_pesos(){
                                                       
            BufferedWriter bw=null;
            FileWriter fw=null;
            double []y = null;
            try{
                File datos=new File("C:\\Users\\Santiago Candia\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto\\src\\proyecto\\pesos.txt");
                fw=new FileWriter(datos.getAbsoluteFile(),true);
                bw= new BufferedWriter(fw); 
                 for (int j =1; j < TotalCapas; j++){
                    for (int i = 1; i <= neuronasporcapa[j]; i++){                        
                        y=(nuevos_pesos[j][i]);
                        
                        
                    }
                    for (int i = 1; i <= neuronasporcapa[j]; i++){
                        
                        String info=String.valueOf(y[i]);                                                                                  
                        bw.write(info+"\n");                                    
                    }
                }
                
                bw.close();
                fw.close();                
            
            }
            catch(IOException e){
                System.err.println("error "+e.getMessage()); 
            }             
        }
        
        
        
}