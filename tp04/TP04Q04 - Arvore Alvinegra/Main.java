import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

class No {
    public Serie elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.
    public boolean cor;

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
    public No(Serie elemento) {
        this(elemento, false, null, null);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(Serie elemento, boolean cor) {
        this(elemento, cor, null, null);
      }

      public No(Serie elemento, boolean cor, No esq, No dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
      }
}

class ArvoreBinaria{
	private No raiz; // Raiz da arvore.
    public static int contador;


	/**
	 * Construtor da classe.
	 */
	public ArvoreBinaria() {
		raiz = null;
	}

	public boolean pesquisar(String x) {
        System.out.print("raiz ");
		return pesquisar(x, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String x, No i) {

        if(i != null){
        // System.out.println(x + " " + i.elemento.getName());
        }

      boolean resp;
		if (i == null) {
         resp = false;
         System.out.print("NAO");

      } else if (x.compareTo(i.elemento.getName()) == 0 ) {
          contador++;
        //   System.out.println(x + " " + i.elemento.getName() + " ACHOU");
         resp = true;
         System.out.print("SIM");

      } else if (x.compareTo(i.elemento.getName()) < 0) {
        contador++;
        System.out.print("esq ");
         resp = pesquisar(x, i.esq);

      } else {
        contador++;
        System.out.print("dir ");
         resp = pesquisar(x, i.dir);
      }
      return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}

    public void inserir(Serie elemento)  {
        // Se a arvore estiver vazia
        if (raiz == null) {
           raiz = new No(elemento);

  
        // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
           if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
              raiz.esq = new No(elemento);
           } else {
              raiz.dir = new No(elemento);
           }
  
        // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
           if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
              raiz.esq = new No(elemento);
  
           } else if (elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0) {
              raiz.esq = new No(raiz.elemento);
              raiz.elemento = elemento;
  
           } else {
              raiz.esq = new No(raiz.elemento);
              raiz.elemento = raiz.dir.elemento;
              raiz.dir.elemento = elemento;
           }
           raiz.esq.cor = raiz.dir.cor = false;
  
        // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
           if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
              raiz.dir = new No(elemento);
  
           } else if (elemento.getName().compareTo(raiz.esq.elemento.getName()) > 0) {
              raiz.dir = new No(raiz.elemento);
              raiz.elemento = elemento;
  
           } else {
              raiz.dir = new No(raiz.elemento);
              raiz.elemento = raiz.esq.elemento;
              raiz.esq.elemento = elemento;
           }
           raiz.esq.cor = raiz.dir.cor = false;
  
        // Senao, a arvore tem tres ou mais elementos
        } else {
           inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
     }


     private void inserir(Serie elemento, No bisavo, No avo, No pai, No i) {
      if (i == null) {
         if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
            i = pai.esq = new No(elemento, true);
         } else {
            i = pai.dir = new No(elemento, true);
         }
         if (pai.cor == true) {
            balancear(bisavo, avo, pai, i);
         }
      } else {
         // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
         if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if (i == raiz) {
               i.cor = false;
            } else if (pai.cor == true) {
               balancear(bisavo, avo, pai, i);
            }
         }
         if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
            inserir(elemento, avo, pai, i, i.esq);
         } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
            inserir(elemento, avo, pai, i, i.dir);
         } else {

         }
      }
   }

	/**
	 * Metodo publico para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserirPai(Serie x) throws Exception {
      if(raiz == null){
         raiz = new No(x);
        } else if (x.getName().compareTo(raiz.elemento.getName()) < 0) {
		   inserirPai(x, raiz.esq, raiz);
        } else if (x.getName().compareTo(raiz.elemento.getName()) > 0) {
		   inserirPai(x, raiz.dir, raiz);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}

	/**
	 * Metodo privado recursivo para inserirPai elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @param pai No superior ao em analise.
	 * @throws Exception Se o elemento existir.
	 */
	private void inserirPai(Serie x, No i, No pai) throws Exception {
		if (i == null) {
         if(x.getName().compareTo(pai.elemento.getName()) < 0){
            pai.esq = new No(x);
         } else {
            pai.dir = new No(x);
         }
      } else if (x.getName().compareTo(i.elemento.getName()) < 0) {
         inserirPai(x, i.esq, i);
      } else if (x.getName().compareTo(i.elemento.getName()) > 0) {
         inserirPai(x, i.dir, i);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}





	/**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No maiorEsq(No i, No j) {

      // Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

      // Existe no a direita.
		} else {
         // Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

	/**
	 * Metodo que retorna o maior elemento da ??rvore
	 * @return int maior elemento da ??rvore
	 */
   public Serie getMaior(){
      Serie resp = new Serie();

      if(raiz != null){
         No i;
         for(i = raiz; i.dir != null; i = i.dir);
         resp = i.elemento;
      }

      return resp;
   }


	/**
	 * Metodo que retorna o menor elemento da ??rvore
	 * @return int menor elemento da ??rvore
	 */
   public Serie getMenor(){
    Serie resp = new Serie();

      if(raiz != null){
         No i;
         for(i = raiz; i.esq != null; i = i.esq);
         resp = i.elemento;
      }

      return resp;
   }


	/**
	 * Metodo que retorna a altura da ??rvore
	 * @return int altura da ??rvore
	 */
   public int getAltura(){
      return getAltura(raiz, 0);
   }


	/**
	 * Metodo que retorna a altura da ??rvore
	 * @return int altura da ??rvore
	 */
   public int getAltura(No i, int altura){
      if(i == null){
         altura--;
      } else {
         int alturaEsq = getAltura(i.esq, altura + 1);
         int alturaDir = getAltura(i.dir, altura + 1);
         altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
      }
      return altura;
   }


	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover2(String x) throws Exception {
      if (raiz == null) {
         throw new Exception("Erro ao remover2!");
      } else if(x.compareTo(raiz.elemento.getName()) < 0){
         remover2(x, raiz.esq, raiz);
      } else if (x.compareTo(raiz.elemento.getName()) > 0){
         remover2(x, raiz.dir, raiz);
      } else if (raiz.dir == null) {
         raiz = raiz.esq;
      } else if (raiz.esq == null) {
         raiz = raiz.dir;
      } else {
         raiz.esq = maiorEsq(raiz, raiz.esq);
      }
   }

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @param pai do No em analise.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private void remover2(String x, No i, No pai) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao remover2!");
      } else if (x.compareTo(i.elemento.getName()) < 0) {
         remover2(x, i.esq, i);
      } else if (x.compareTo(i.elemento.getName()) > 0) {
         remover2(x, i.dir, i);
      } else if (i.dir == null) {
         pai = i.esq;
      } else if (i.esq == null) {
         pai = i.dir;
      } else {
         i.esq = maiorEsq(i, i.esq);
		}
	}

   public Serie getRaiz() throws Exception {
      return raiz.elemento;
   }

   public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
      return igual(a1.raiz, a2.raiz);
   }

   private static boolean igual (No i1, No i2){
      boolean resp;
      if(i1 != null && i2 != null){
         resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
      } else if(i1 == null && i2 == null){
         resp = true;
      } else {
         resp = false; 
      }
      return resp;
   }

   private void balancear(No bisavo, No avo, No pai, No i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
        // 4 tipos de reequilibrios e acoplamento
        if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
            if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0) {
                avo = rotacaoEsq(avo);
            } else {
                avo = rotacaoDirEsq(avo);
            }
        } else { // rotacao a direita ou esquerda-direita
            if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                avo = rotacaoDir(avo);
            } else {
                avo = rotacaoEsqDir(avo);
            }
        }
        if (bisavo == null) {
            raiz = avo;
        } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
            bisavo.esq = avo;
        } else {
            bisavo.dir = avo;
        }
        // reestabelecer as cores apos a rotacao
        avo.cor = false;
        avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)
    }

    public No rotacaoDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    public No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    public No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    public No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }

    static void log(long time){ 

        try {
            File myObj = new File("matri??culaAvinegra.txt");
            if (myObj.createNewFile()) {
            } else {
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            FileWriter myWriter = new FileWriter("matri??culaAvinegra.txt");
            myWriter.write("728764" + "\t" + time + "\t" + contador);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    
    }
}


class Serie{
    //declara????o dos atributos
    private String name;
    private String format;
    private String duration;
    private String country;
    private String language;
    private String broadcaster;
    private String streaming;
    private int seasons;
    private int episodes;
    //construtor prim??rio
    public Serie(){
        name = "";
        format = "";
        duration = "";
        country = "";
        language = "";
        broadcaster = "";
        streaming = "";
        seasons = 0;
        episodes = 0;
    }
    //construtor secund??rio
    public Serie(String name, String format, String duration, String country, String language, String broadcaster, String streaming, int seasons, 
    int episodes){
        this.name = name;
        this.format = format;
        this.duration = duration;
        this.country = country;
        this.language = language;
        this.broadcaster = broadcaster;
        this.streaming = streaming;
        this.seasons = seasons;
        this.episodes = episodes;
    }
    //m??todo para setar o atributo name
    public void setName(String name){
        this.name = name;
    }
    //m??todo para setar o atributo formato
    public void setFormat(String format){
        this.format = format;
    }
    //m??todo para setar o atributo duration
    public void setDuration(String duration){
        this.duration = duration;
    }
    //m??todo para setar o atributo country
    public void setCountry(String country){
        this.country = country;
    }
    //m??todo para setar o atributo language
    public void setLanguage(String language){
        this.language = language;
    }
    //m??todo para setar o atributo broadcaster
    public void setBroadcaster(String broadcaster){
        this.broadcaster = broadcaster;
    }
    //m??todo para setar o atributo streaming
    public void setStreaming(String streaming){
        this.streaming = streaming;
    }
    //m??todo para setar o atributo seasons
    public void setSeasons(int seasons){
        this.seasons = seasons;
    }
    //m??todo para setar o atributo episodes
    public void setEpisodes(int episodes){
        this.episodes = episodes;
    }
    //m??todo para retornar o atributo name
    public String getName(){ 
        return this.name; 
    }
    //m??todo para retornar o atributo format
    public String getFormat(){ 
        return this.format; 
    }
    //m??todo para retornar o atributo duration
    public String getDuration(){ 
        return this.duration; 
    }
    //m??todo para retornar o atributo country
    public String getCountry(){ 
        return this.country; 
    }
    //m??todo para retornar o atributo language
    public String getLanguage(){ 
        return this.language; 
    }
    //m??todo para retornar o atributo broadcaster
    public String getBroadcaster(){ 
        return this.broadcaster; 
    }
    //m??todo para retornar o atributo streaming
    public String getStreaming(){ 
        return this.streaming; 
    }
    //m??todo para retornar o atributo seasons
    public int getSeasons(){ 
        return this.seasons; 
    }
    //m??todo para retornar o atributo episodes
    public int getEpisodes(){ 
        return this.episodes; 
    }
    //m??todo para clonar a classe
    public Serie clone(){
        Serie resp = new Serie();
        resp.name = this.name;
        resp.format = this.format;
        resp.duration = this.duration;
        resp.country = this.country;
        resp.language = this.language;
        resp.broadcaster = this.broadcaster;
        resp.streaming = this.streaming;
        resp.seasons = this.seasons;
        resp.episodes = this.episodes;
        return resp;
    }
    //m??todo para printar a classe
    public void printClass(){
        System.out.println(this.name + " " + this.format + " " + this.duration + " " + this.country + " " + this.language + " " + this.broadcaster + " " +
        this.streaming + " " + this.seasons + " " + this.episodes);
    }
    //m??todo para tratar a linha, deixar apenas n??meros e converter o retorno de String para Integer
    public int justInt(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um n??mero ele ?? concatenado a vari??vel resp
                resp += line.charAt(i);
            } else { //caso seja outro caracter, o i recebe o valor da condi????o de parada e o m??todo de repeti????o ?? encerrado
                i = line.length();
            }
        }
        return Integer.parseInt(resp); //convers??o da string resp para n??mero inteiro a ser retornado
    }
    //m??todo para a remo????o das tags da linha lida do arquivo para retornar apenas o que ?? desejado
    public String removeTags(String line){
        String resp = "";
        int i = 0;
        while(i < line.length()){ //enquanto i for menor que o tamanho da String linha
            if(line.charAt(i) == '<'){ // ?? testado para verificar se o contador i ainda est?? dentro das tags
                i++;
                while(line.charAt(i) != '>') i++; //ao encontrar o sinal de fechamento das tags o la??o de repeti????o ?? encerrado
            } else if(line.charAt(i) == '&'){ //mesmo tratamento de cima mas para outras exce????es presentes em alguns outros arquivos
                i++;
                while(line.charAt(i) != ';') i++;
            } else { //o que estiver fora das tags ?? concatenado a String resp a ser retornada
                resp += line.charAt(i);
            }
            i++;
        }
        //System.out.println(resp);
        return resp;
    }
    //m??todo para tratar o nome do arquivo e retornar o nome da s??rie
    public String searchName(String fileName){
        String resp = "";
        for(int i = 0; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ //caso o caracter na posi????o i seja igual ao '_' a vari??vel resp recebe um espa??o em branco
                resp += ' ';
            } else { //caso n??o tenha espa??o em branco o caracter ?? concatenado ?? string resp
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 ??ltimos caracteres relacionados ?? extens??o do arquivo
    }
    //m??todo para leitura do arquivo .html e tratamento das linhas
    public void readClass(String fileName){
        String line;
        String resp = "";
        String file = "/tmp/series/" + fileName;
        try {
            FileReader fileReader = new FileReader(file); //declara????o da vari??vel fileReader que ser?? recebida pelo bufferedReader

            BufferedReader br = new BufferedReader(fileReader); //declara????o do bufferedReader para leitura do arquivo
            
            //set nome da s??rie
            this.name = searchName(fileName);
            
            //set Formato da s??rie
            while(!br.readLine().contains("Formato"));
            this.format = removeTags(br.readLine());

            //set dura????o da s??rie
            while(!br.readLine().contains("Dura????o"));
            this.duration = removeTags(br.readLine());

            //set pa??s da s??rie
            while(!br.readLine().contains("Pa??s de origem"));
            this.country = removeTags(br.readLine());
            this.country = this.country.trim();

            //set idioma da s??rie
            while(!br.readLine().contains("Idioma original"));
            this.language = removeTags(br.readLine());

            //set emissora da s??rie
            while(!br.readLine().contains("Emissora de televis??o"));
            this.broadcaster = removeTags(br.readLine());

            //set transmiss??o original da s??rie
            while(!br.readLine().contains("Transmiss??o original"));
            this.streaming = removeTags(br.readLine());

            //set temporadas da s??rie
            while(!br.readLine().contains("N.?? de temporadas"));
            this.seasons = justInt(removeTags(br.readLine()));

            //set epis??dios da s??rie
            while(!br.readLine().contains("N.?? de epis??dios"));
            this.episodes = justInt(removeTags(br.readLine()));
            
            
            //fechamento do bufferedReader
            br.close();         
        //Tratamento de exce????es
        } catch(FileNotFoundException e) {
                          
        } catch(IOException e) {
            
        }
    }
}
public class Main {
	//Fun????o que retorna o ponto de parada da leitura.
    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

	public static void main(String args[])  throws Exception{

		Scanner teclado = new Scanner(System.in);
		long startTime = System.nanoTime(); 
        String input;
        int i = 0;
        ArvoreBinaria arvore = new ArvoreBinaria();

        //Leitura do pub.in
		while(true){
            Serie serie = new Serie();
			input = teclado.nextLine();
            if(returnEnd(input) == true)
                break;
            serie.readClass(input);
            arvore.inserir(serie);
		}		

        while(true){
            input = teclado.nextLine();
            if(returnEnd(input) == true){
                break; 
            }
            arvore.pesquisar(input);
            System.out.print("\n");
        }

		long endTime = System.nanoTime(); //Fim do timer
		long duration = (endTime - startTime);  //C??lculo do tempo

        arvore.log(duration);
		teclado.close();
    }
}