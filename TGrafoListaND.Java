//definição de uma classe para armazenar um grafo NÃO dirigido, usando lista de adjacência
// OBS: esta classe reaproveita a classe TNo ja declarada em TGrafoLista.java
public class TGrafoListaND{
	// atributos privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	TNo adj[]; // um vetor onde cada entrada guarda o inicio de uma lista

	public TGrafoListaND( int n ) {
	    this.n = n;
	    this.m = 0;
	    TNo adjac[] = new TNo[n];
		for(int i = 0; i< n; i++)
			adjac[i]=null;	
	    this.adj = adjac;
	}

	// insere aresta não dirigida v-w (nos dois sentidos), conta 1 aresta só
	public void insereA( int v, int w) {
	    if (existeAresta(v, w)) return;
	    insereOrdenado(v, w);
	    insereOrdenado(w, v);
	    m++;
	}

	// auxiliar: insere w na lista ordenada de v
	private void insereOrdenado(int v, int w) {
	    TNo no = adj[v];
	    TNo ant = null;
	    while (no != null && w >= no.w) {
	        if (w == no.w) return;
	        ant = no;
	        no = no.prox;
	    }
	    TNo novoNo = new TNo();
	    novoNo.w = w;
	    novoNo.prox = no;
	    if (ant == null) {
	        adj[v] = novoNo;
	    } else {
	        ant.prox = novoNo;
	    }
	}

	private boolean existeAresta(int v, int w) {
	    TNo no = adj[v];
	    while (no != null) {
	        if (no.w == w) return true;
	        no = no.prox;
	    }
	    return false;
	}

	// remove aresta v-w dos dois sentidos
	public void removeA( int v, int w) {
	    if (!existeAresta(v, w)) return;
	    removeDaLista(v, w);
	    removeDaLista(w, v);
	    m--;
	}

	private void removeDaLista(int v, int w) {
	    TNo no = adj[v];
	    TNo ant = null;
	    while (no != null && no.w != w) {
	        ant = no;
	        no = no.prox;
	    }
	    if (no != null) {
	        if (ant == null) {
	            adj[v] = no.prox;
	        } else {
	            ant.prox = no.prox;
	        }
	    }
	}

	public void show() {
	    System.out.print("n: " + n);
	    System.out.print("\nm: " + m + "\n");
	    for( int i=0; i < n; i++){
	    	System.out.print("\n" + i + ": ");
	        TNo no = adj[i];
	        while( no != null ){
	        	System.out.print(no.w + " ");
	            no = no.prox;
	        }
	    }
	    System.out.print("\n\nfim da impressao do grafo.\n");
	}

	public int degree(int v) {
	    int grau = 0;
	    TNo no = adj[v];
	    while (no != null) {
	        grau++;
	        no = no.prox;
	    }
	    return grau;
	}

	//exe28: remove um vértice v do grafo NÃO dirigido, junto com todas
	// as arestas associadas a ele
	public void removeVertice(int v) {
	    // 1) remove v de todas as listas onde ele aparece como vizinho
	    for (int i = 0; i < n; i++) {
	        if (i != v) removeDaLista(i, v);
	    }

	    // 2) monta o novo vetor de cabeças, sem a posição de v, e
	    //    ajustando os índices que eram maiores que v (descem 1)
	    int novoN = n - 1;
	    TNo novoAdj[] = new TNo[novoN];
	    int novoIndice = 0;
	    for (int i = 0; i < n; i++) {
	        if (i == v) continue;
	        novoAdj[novoIndice] = ajustaIndices(adj[i], v);
	        novoIndice++;
	    }

	    adj = novoAdj;
	    n = novoN;

	    // 3) recalcula m contando os nós que restaram (cada aresta aparece 2x)
	    int totalMarcado = 0;
	    for (int i = 0; i < n; i++) {
	        TNo no = adj[i];
	        while (no != null) {
	            totalMarcado++;
	            no = no.prox;
	        }
	    }
	    m = totalMarcado / 2;
	}

	//exe30: grafo não dirigido é completo? (toda dupla i!=j tem aresta)
	public int isCompleto() {
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (i != j && !existeAresta(i, j)) {
	                return 0;
	            }
	        }
	    }
	    return 1;
	}

	// auxiliar: percorre uma lista e subtrai 1 de todo w > vRemovido
	private TNo ajustaIndices(TNo lista, int vRemovido) {
	    TNo no = lista;
	    while (no != null) {
	        if (no.w > vRemovido) no.w = no.w - 1;
	        no = no.prox;
	    }
	    return lista;
	}
}
