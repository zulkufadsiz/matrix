package matrix;
final public class Matrix {
    private final int M;             // satir
    private final int N;             // sutun sayisi
    private final double[][] data;   // M-N tipinde dizi

    // M-N matrisi olustur
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // Matris olustur
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // diziyi yapıcı metoda kopyala
    private Matrix(Matrix A) { this(A.data); }

    //0-1 arasında rastgele dizi olustur ve degerleri ata
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // n boyutunda bir matrisi olustur
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // sutun satır degistir
    private void takas(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // transpoz al
    public Matrix transpoz() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // matrislerde toplama
    public Matrix topla(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Matris boyut hatası");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // matrislerde çikarma
    public Matrix cikar(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // iki matrisin eşitligi
    public boolean esit(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Matris boyutunda hata.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // iki matrisin çarpımı
    public Matrix carp(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Matris boyut hatası.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }
    
    
    // iki matrisin bolumu
    public Matrix bol(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Matris boyut hatası.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] / B.data[k][j]);
        return C;
    }


    
    public double determinant(double A[][],int N)
    {
        double det=0;
        if(N == 1)
        {
            det = A[0][0];
        }
        else if (N == 2)
        {
            det = A[0][0]*A[1][1] - A[1][0]*A[0][1];
        }
        else
        {
            det=0;
            for(int j1=0;j1<N;j1++)
            {
                double[][] m = new double[N-1][];
                for(int k=0;k<(N-1);k++)
                {
                    m[k] = new double[N-1];
                }
                for(int i=1;i<N;i++)
                {
                    int j2=0;
                    for(int j=0;j<N;j++)
                    {
                        if(j == j1)
                            continue;
                        m[i-1][j2] = A[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0,1.0+j1+1.0)* A[0][j1] * determinant(m,N-1);
            }
        }
        return det;
    }
    // Matrisi yazdir
    public void yazdir() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }
    
    



    // test client
    public static void main(String[] args) {
        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };
        Matrix D = new Matrix(d);
        D.yazdir();        
        System.out.println();

        Matrix A = Matrix.random(5, 5);
        A.yazdir(); 
        System.out.println();

        A.takas(1, 2);
        A.yazdir(); 
        System.out.println();

        Matrix B = A.transpoz();
        B.yazdir(); 
        System.out.println();

      
        System.out.println();

        A.topla(B).yazdir();
        System.out.println();

        B.carp(A).yazdir();
        System.out.println();

        //Matris esitligi    
        System.out.println(A.carp(B).esit(B.carp(A)));
        System.out.println();
        
        //Rastgele matris olustur
        Matrix b = Matrix.random(5, 1);
        b.yazdir();
        System.out.println();

        
        
        
    }
}
