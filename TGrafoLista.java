
//definicao da classe de nós da lista
class TNo{ // define uma struct (registro)
	public	int w;  // vértice que é adjacente ao elemento da lista
	public TNo prox;
}

//definição de uma classe para armezanar um grafo
public class TGrafoLista{
	// atributos privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	TNo adj[]; // um vetor onde cada entrada guarda o inicio de uma lista
	// métodos públicos
	// Construtor do grafo com a lista de
	// adjacência
	public TGrafo( int n ) {
	    // aloca a estrutura TGrafo
	    this.n = n;
	    this.m = 0;
	    // aloca m vetor para guardar lista de adjacencias
	    TNo adjac[] = new TNo[n];
	    // Inicia o vetor com nullL
		for(int i = 0; i< n; i++)
			adjac[i]=null;	
	    this.adj = adjac;
	};
	/*
	Método que cria uma aresta v-w no grafo. O método supõe que
	v e w são distintos, positivos e menores que V.
	Se o grafo já tem a aresta v-w, o método não faz nada.
	O método também atualiza a quantidade de arestas no grafo.
	*/
	public void insereA( int v, int w) {
		
	    TNo novoNo;
	    // anda na lista para chegar ao final
	    TNo no = adj[v];
	    TNo ant = null;
	    // anda na lista enquanto no != NULL E w  > no->w
	    while( no != null && w >= no.w ){
	        if( w == no.w)
	            return;
	        ant = no;
	        no = no.prox;
	    };
	    // cria o novo No para guardar w
	    novoNo = new TNo();
	    novoNo.w = w;
	    novoNo.prox = no;
	    // atualiza a lista
	    if( ant == null){
	        // insere no inicio
	        adj[v] = novoNo;
	    } else
	        // insere no final
	        ant.prox = novoNo;
	    m++;	
	}
	
	/*
	Método que remove do grafo a aresta que tem ponta inicial v
	e ponta final w. O método supõe que v e w são distintos,
	positivos e menores que V. Se não existe a aresta v-w,
	o método não faz nada. O método também atualiza a
	quantidade de arestas no grafo.
	*/
	public void removeA( int v, int w) {
	    // Obtém o início da lista do vértice v
	    TNo no = adj[v];
	    TNo ant = null;
	    // Percorre a lista do vértice v
	    // procurando w (se adjacente)
	    while( no != null && no.w != w ){
	    		ant = no;
	    		no = no.prox;
	    }
	    // Se w é adjacente, remove da lista de v
	    if (no != null){
	    	ant.prox = no.prox;
	    	no = null;
	    	m--;
		}	
	}
	/*
	Para cada vértice v do grafo, este método imprime, em
	uma linha, todos os vértices adjacentes ao vértice v
	(vizinhos ao vértice v).
	*/
	public void show() {
	    System.out.print("n: " + n);
	    System.out.print("\nm: " + m + "\n");
	    for( int i=0; i < n; i++){
	    	System.out.print("\n" + i + ": ");
	        // Percorre a lista na posição i do vetor
	        TNo no = adj[i];
	        while( no != null ){
	        	System.out.print(no.w + " ");
	            no = no.prox;
	        }
	    }
	    System.out.print("\n\nfim da impressao do grafo.\n");
	}
}

//exe18
public int inDegree(int v) {
    int grau = 0;
    for (int i = 0; i < n; i++) {
        TNo no = adj[i];
        while (no != null) {
            if (no.w == v) grau++;
            no = no.prox;
        }
    }
    return grau;
}
//exe19
public int outDegree(int v) {
    int grau = 0;
    TNo no = adj[v];
    while (no != null) {
        grau++;
        no = no.prox;
    }
    return grau;
}


//exe20
public int isIgual(TGrafo outro) {
    if (this.n != outro.n || this.m != outro.m) return 0;

    for (int v = 0; v < n; v++) {
        TNo no1 = this.adj[v];
        TNo no2 = outro.adj[v];
        while (no1 != null && no2 != null) {
            if (no1.w != no2.w) return 0;
            no1 = no1.prox;
            no2 = no2.prox;
        }
        // se sobrou nó em uma lista e não na outra, listas de tamanhos diferentes
        if (no1 != null || no2 != null) return 0;
    }
    return 1;
}

//exe21
public int isIgual(TGrafo outro) {
    if (this.n != outro.n || this.m != outro.m) return 0;

    for (int v = 0; v < n; v++) {
        TNo no1 = this.adj[v];
        TNo no2 = outro.adj[v];
        while (no1 != null && no2 != null) {
            if (no1.w != no2.w) return 0;
            no1 = no1.prox;
            no2 = no2.prox;
        }
        // se sobrou nó em uma lista e não na outra, listas de tamanhos diferentes
        if (no1 != null || no2 != null) return 0;
    }
    return 1;
}

//exe22
public GrafoLista.TGrafo paraLista() {
    GrafoLista.TGrafo lista = new GrafoLista.TGrafo(n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (adj[i][j] != Float.POSITIVE_INFINITY) {
                lista.insereA(i, j);
            }
        }
    }
    return lista;
}


//exe23
public void inverteListas() {
    for (int v = 0; v < n; v++) {
        TNo anterior = null;
        TNo atual = adj[v];
        while (atual != null) {
            TNo proximo = atual.prox; // guarda o próximo antes de sobrescrever
            atual.prox = anterior;    // vira o ponteiro
            anterior = atual;
            atual = proximo;
        }
        adj[v] = anterior; // anterior agora é o novo início da lista
    }
}

//exe24
public int isFonte(int v) {
    return (outDegree(v) > 0 && inDegree(v) == 0) ? 1 : 0;
}

//exe25
public int isSorvedouro(int v) {
    return (inDegree(v) > 0 && outDegree(v) == 0) ? 1 : 0;
}

//exe26
// auxiliar: existe a aresta v->w?
private boolean existeAresta(int v, int w) {
    TNo no = adj[v];
    while (no != null) {
        if (no.w == w) return true;
        no = no.prox;
    }
    return false;
}

public int isSimetrico() {
    for (int v = 0; v < n; v++) {
        TNo no = adj[v];
        while (no != null) {
            if (!existeAresta(no.w, v)) return 0;
            no = no.prox;
        }
    }
    return 1;
}

//exe27

public static TGrafo lerArquivo(String nomeArq) throws java.io.FileNotFoundException {
    java.util.Scanner sc = new java.util.Scanner(new java.io.File(nomeArq));
    int V = sc.nextInt();
    int A = sc.nextInt();
    TGrafo g = new TGrafo(V);
    for (int i = 0; i < A; i++) {
        int v = sc.nextInt();
        int w = sc.nextInt();
        g.insereA(v, w);
    }
    sc.close();
    return g;
}

//exe29
public void removeVertice(int v) {
    // 1) remove v de todas as listas onde ele aparece como vizinho
    //    (ou seja, remove toda aresta i->v)
    for (int i = 0; i < n; i++) {
        if (i != v) removerDaLista(i, v);
    }

    // 2) monta o novo vetor de cabeças sem a posição de v, ajustando
    //    índices maiores que v (descem 1 casa)
    int novoN = n - 1;
    TNo novoAdj[] = new TNo[novoN];
    int novoIndice = 0;
    for (int i = 0; i < n; i++) {
        if (i == v) continue; // pula a lista do próprio v (suas arestas de saída somem junto)
        novoAdj[novoIndice] = ajustaIndices(adj[i], v);
        novoIndice++;
    }

    adj = novoAdj;
    n = novoN;

    // 3) recalcula m contando os nós que restaram
    int totalMarcado = 0;
    for (int i = 0; i < n; i++) {
        TNo no = adj[i];
        while (no != null) {
            totalMarcado++;
            no = no.prox;
        }
    }
    m = totalMarcado;
}


//exe30
// na TGrafo (dirigido) — precisa das DUAS direções
public int isCompleto() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i != j && (!existeAresta(i, j) || !existeAresta(j, i))) {
                return 0;
            }
        }
    }
    return 1;
}
