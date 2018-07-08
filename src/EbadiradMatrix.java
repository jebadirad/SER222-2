
/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * Contains some testing in the form of console outputs that we would need
 * to compare to a calculated output to verify that they are correct answers.
 * Some methods throw exceptions if the Matrix Dimensions are not identical to
 * the matrix that it is compared with.
 *
 * Completion Time: 5 hours
 *
 * @author JonDavid Ebadirad, Ruben Acuna
 * @version 1.0
 */
public class EbadiradMatrix implements Matrix {

    //TODO: implement interface.
    private int[][] matrix;
    public EbadiradMatrix(int [][] matrix){

        if(matrix.length > 0 ){
            this.matrix = new int[matrix.length][matrix[0].length];
            for(int i = 0; i < matrix.length; i ++){
                for(int j = 0; j < matrix[i].length; j ++){
                    this.matrix[i][j] = matrix[i][j];
                }
            }
        }
        else{
            this.matrix = new int[0][0];
        }
    }
    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

        Matrix m1 = new EbadiradMatrix(data1);
        Matrix m2 = new EbadiradMatrix(data2);
        Matrix m3 = new EbadiradMatrix(data3);
        Matrix m4 = new EbadiradMatrix(data4);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true

        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("m2 * m3:\n" + m2.multiply(m3));
        System.out.println("m1 + m2:\n" + m2.plus(m3));
        System.out.println("m1 - m2:\n" + m2.minus(m3));

        try{
            System.out.println("m1 * m2 : exception will be thrown \n" + m1.multiply(m2));
        }
        catch(RuntimeException e){
            System.out.println("m1 * m2 : exception was thrown");
        }

        try{
            System.out.println("m1 + m2 : exception will be thrown \n" + m1.plus(m2));
        }
        catch(RuntimeException e){
            System.out.println("m1 + m2 : exception was thrown");
        }
        try{
            System.out.println("m1 - m2 : exception will be thrown \n" + m1.minus(m2));
        }
        catch(RuntimeException e){
            System.out.println("m1 - m2 : exception was thrown");
        }
    }


    /**
     * Returns the element at particular point in the matrix.
     *
     * @param y y position
     * @param x x position
     * @return element
     */
    @Override
    public int getElement(int y, int x) {
        return this.matrix[y][x];
    }

    /**
     * Returns the number of rows in the matrix.
     *
     * @return rows
     */
    @Override
    public int getRows() {
        return this.matrix.length;
    }

    /**
     * Returns the number of columns in the matrix.
     *
     * @return columns
     */
    @Override
    public int getColumns() {
        int cols = 0;
        if(this.matrix.length > 0){
            cols = this.matrix[0].length;
        }
        return cols;
    }

    /**
     * Returns this matrix scaled by a factor. That is, computes kA where k is a
     * constant and A is a matrix (this object).
     *
     * @param scalar scalar
     * @return matrix
     */
    @Override
    public Matrix scale(int scalar) {
        int[][] scaledMatrix = new int[this.getRows()][this.getColumns()];
        for(int i = 0; i < this.getRows(); i ++){
            for(int j = 0; j < this.getColumns(); j++){
                scaledMatrix[i][j] = this.getElement(i,j) * scalar;
            }
        }
        return new EbadiradMatrix(scaledMatrix);
    }

    /**
     * Returns this matrix added with another matrix. That is, computes A+B
     * where A and B are matrices (this object, and another respectively).
     *
     * @param other addend
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    @Override
    public Matrix plus(Matrix other) {
        if(this.getColumns() == other.getColumns() && this.getRows() == other.getRows()){
            int[][] addedMatrix = new int[this.getRows()][this.getColumns()];
              for(int i = 0; i < this.getRows(); i ++){
                for(int j = 0; j < this.getColumns(); j ++){
                    addedMatrix[i][j]= this.getElement(i,j) + other.getElement(i,j);
                }
              }
              return new EbadiradMatrix(addedMatrix);
        }else{
            throw new RuntimeException("Matrices do not have matching dimensions");
        }
    }

    /**
     * Returns this matrix subtracted by another matrix. That is, computes A-B
     * where A and B are matrices (this object, and another respectively).
     *
     * @param other subtrahend
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    @Override
    public Matrix minus(Matrix other) {
        if(this.getColumns() == other.getColumns() && this.getRows() == other.getRows()){
            int[][] minusMatrix = new int[this.getRows()][this.getColumns()];
            for(int i = 0; i < this.getRows(); i ++){
                for(int j = 0; j < this.getColumns(); j ++){
                    minusMatrix[i][j]= this.getElement(i,j) - other.getElement(i,j);
                }
            }
            return new EbadiradMatrix(minusMatrix);
        }else{
            throw new RuntimeException("Matrices do not have matching dimensions");
        }
    }

    /**
     * Returns this matrix multiplied by another matrix (using dot products).
     * That is, computes AB where A and B are matrices (this object, and another
     * respectively).
     *
     * @param other multiplicand
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    @Override
    public Matrix multiply(Matrix other) {
        if(this.getColumns() == other.getColumns() && this.getRows() == other.getRows()){
            int [][] dotProduct = new int[this.getRows()][this.getColumns()];
            //doesnt matter what rows / columns from which matrix we use since we are only
            //accepting matrices that have identical dimensions. 
            for(int i = 0; i < this.getRows(); i ++){
                for(int j =0; j < this.getColumns(); j++){
                    for(int k = 0; k < this.getColumns(); k ++){
                        dotProduct[i][j] += this.getElement(i, k) * other.getElement(k,j);
                    }
                }
            }
            return new EbadiradMatrix(dotProduct);

        }else{
            throw new RuntimeException("Matrices do not have matching dimensions");
        }
    }
    /**
     * Returns a string representation of this matrix. A new line character will
     * separate each row, while a space will separate each column.
     * @return string representation
     */
    @Override
    public String toString(){
        String returnString = "";
        for(int i = 0; i < this.matrix.length; i ++){
            for(int j = 0; j < this.matrix[i].length; j ++){
                returnString += this.matrix[i][j] + " ";
            }
            returnString += "\n";
        }
        return returnString;
    }
    /**
     * Returns true if this matrix matches another matrix.
     * @param other another matrix
     * @return equality
     */
    @Override
    public boolean equals(Object other){
        boolean equal = true;
        if(other instanceof  Matrix){
            if(this.getColumns() == ((Matrix) other).getColumns()){
                if(this.getRows() == ((Matrix)other).getRows()){
                    for(int i = 0; i < this.getRows(); i ++){
                        for(int j = 0; j < this.getColumns(); j ++){
                            if(this.getElement(j, i) != ((Matrix)other).getElement(j,i)){
                                equal = false;
                            }
                        }
                    }
                }
                else{
                    equal = false;
                }
            }
            else{
                equal = false;
            }
        }else{
            equal = false;
        }
        return equal;
    }
}