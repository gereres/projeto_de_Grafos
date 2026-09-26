//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafoND {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	int adj[][]; //matriz de adjacência
	// Métodos Públicos
	public TGrafoND( int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new int [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=0;	
	}

	// Insere uma aresta no Grafo tal que
	// v é adjacente a w
	public void insereA(int v, int w) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == 0 && adj[w][v]==0){
			adj[w][v] = 1;
	        adj[v][w] = 1;
	        m++; // atualiza qtd arestas
	    }
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] == 1 && adj[w][v]==1){
			adj[w][v] = 0;
	        adj[v][w] = 0;
	        m--; // atualiza qtd arestas
	    }
	}
	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            if(adj[i][w] == 1)
	            	System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}

	//exe9
	public int degree(int v)
	{
		int grau = 0;
		for(int j=0; j<n; j++)
		{
			if(adj[v][j] == 1)
			{
				grau++;
			}
		}
		return grau;
	}


    //exe11
    public void removeVertice(int v) {
        int novoN = n - 1;
        int novaAdj[][] = new int[novoN][novoN]; // já nasce com 0 = sem aresta

        int li = 0;
        for(int i=0; i<n; i++){
            if(i == v) continue;
            int lj = 0;
            for(int j=0; j<n; j++){
                if(j == v) continue;
                novaAdj[li][lj] = adj[i][j];
                lj++;
            }
            li++;
        }

        adj = novaAdj;
        n = novoN;

        int totalMarcado = 0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(adj[i][j] == 1) totalMarcado++;
        m = totalMarcado / 2; // cada aresta aparece 2x na matriz simétrica
    }

    //exe12

    public int isCompleto() {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(i != j && adj[i][j] == 0)
                {
                    return 0; 
                }
            }
        }
        return 1; 
    }


    //exe14:  
    public TGrafoND complementar() {
        TGrafoND comp = new TGrafoND(n);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j && adj[i][j] == 0){
                    comp.insereA(i, j);
                }
            }
        }
        return comp;
    }

    //exe15: verifica a conexidade do grafo não dirigido
	// (0 = conexo, 1 = desconexo), usando DFS com a Pilha
	public int conexidade() {
	    boolean visitado[] = new boolean[n];
	    Pilha pilha = new Pilha(n); // capacidade = nº de vértices
 
	    pilha.push(0);
	    visitado[0] = true;
	    int totalVisitados = 1;
 
	    while (!pilha.isEmpty()) {
	        int atual = pilha.pop();
	        for (int j = 0; j < n; j++) {
	            if (adj[atual][j] == 1 && !visitado[j]) {
	                visitado[j] = true;
	                totalVisitados++;
	                pilha.push(j);
	            }
	        }
	    }
 
	    return (totalVisitados == n) ? 0 : 1;
	}
}
