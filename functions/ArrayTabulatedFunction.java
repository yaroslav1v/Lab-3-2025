package functions;

public class ArrayTabulatedFunction {

    private FunctionPoint[] points_arr;
    private int pointslength;

    public static boolean compareDouble(double a, double b) {
        final double epsilon = 1e-10;
        double diff = a - b;

        if (diff < -epsilon && diff > epsilon) {
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) {
        // проверка левая граница >= правой
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница " + leftX + " >= правой границы " + rightX);
        }

        // проверка количество точек < 2
        if (pointsCount < 2) {
            throw new IllegalArgumentException("Количество точек " + pointsCount + " < 2");
        }

        points_arr = new FunctionPoint[pointsCount];
        pointslength = pointsCount;

        double intervalLength = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            points_arr[i] = new FunctionPoint(leftX + intervalLength * i, 0);
        }
    }

    public ArrayTabulatedFunction(double leftX, double rightX, double[] points) {
        // проверка левая граница >= правой
        if (leftX >= rightX) {
            throw new IllegalArgumentException("Левая граница " + leftX + " >= правой границы " + rightX);
        }

        // проверка количество точек < 2
        if (points.length < 2) {
            throw new IllegalArgumentException("Количество точек " + points.length + " < 2");
        }

        pointslength = points.length;
        points_arr = new FunctionPoint[points.length];
        double intervalLength = (rightX - leftX) / (points.length - 1);

        if (pointslength != 0) {
            for (int i = 0; i < points.length; i++) {
                points_arr[i] = new FunctionPoint(leftX + intervalLength * i, points[i]);
            }
        }
    }

    public double getLeftDomainBorder() {
        return points_arr[0].getX();
    }

    public double getRightDomainBorder(){
        return points_arr[pointslength-1].getX();
    }

    public double getFunctionValue(double x){
        double y;
        int findex=0, sindex=0;

        if(x < getLeftDomainBorder() || x > getRightDomainBorder()){return Double.NaN;}
        if(compareDouble(x,getLeftDomainBorder())){return points_arr[0].getY();}
        if(compareDouble(x,getRightDomainBorder())){return points_arr[pointslength-1].getY();}

        for(int i = 0; i < pointslength-1; i++){
            if(points_arr[i].getX() <= x && points_arr[i + 1].getX() >= x){
                findex = i;
                sindex = i + 1;
                i = pointslength-1;
            }
        }
        return ((points_arr[findex].getY()) + (points_arr[sindex].getY() - points_arr[findex].getY()) * (x - points_arr[findex].getX()) / (points_arr[sindex].getX() - points_arr[findex].getX()));
    }

    public int getPointsCount(){
        return pointslength;
    }

    public FunctionPoint getPoint(int index){
        // проверка индекс за границами
        if (index < 0 || index >= pointslength) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс " + index + " выходит за границы [0, " + (pointslength-1) + "]");
        }

        FunctionPoint point = new FunctionPoint(points_arr[index]);
        return point;
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        // проверка индекс за границами
        if (index < 0 || index >= pointslength) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс " + index + " выходит за границы [0, " + (pointslength-1) + "]");
        }

        // проверка упорядоченность X
        double newX = point.getX();
        double prevX;
        double nextX;

        // Определяем предыдущий X
        if (index > 0) {
            prevX = points_arr[index-1].getX();
        } else {
            prevX = Double.NEGATIVE_INFINITY;
        }

        // Определяем следующий X
        if (index < pointslength - 1) {
            nextX = points_arr[index+1].getX();
        } else {
            nextX = Double.POSITIVE_INFINITY;
        }

        if (newX <= prevX || newX >= nextX) {
            throw new InappropriateFunctionPointException(
                    "X=" + newX + " должен быть строго между " + prevX + " и " + nextX);
        }

        points_arr[index] = new FunctionPoint(point);
    }

    public double getPointX(int index){
        // проверка индекс за границами
        if (index < 0 || index >= pointslength) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс " + index + " выходит за границы [0, " + (pointslength-1) + "]");
        }

        return points_arr[index].getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        // проверка 1: индекс за границами
        if (index < 0 || index >= pointslength) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс " + index + " выходит за границы [0, " + (pointslength-1) + "]");
        }

        // проверка упорядоченность X
        double prevX;
        if (index > 0) {
            prevX = points_arr[index-1].getX();
        } else {
            prevX = Double.NEGATIVE_INFINITY;
        }

        double nextX;
        if (index < pointslength - 1) {
            nextX = points_arr[index+1].getX();
        } else {
            nextX = Double.POSITIVE_INFINITY;
        }

        if (x <= prevX || x >= nextX) {
            throw new InappropriateFunctionPointException(
                    "X=" + x + " должен быть строго между " + prevX + " и " + nextX);
        }

        points_arr[index] = new FunctionPoint(x, points_arr[index].getY());
    }

    public double getPointY(int index){
        // проверка индекс за границами
        if (index < 0 || index >= pointslength) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс " + index + " выходит за границы [0, " + (pointslength-1) + "]");
        }

        return points_arr[index].getY();
    }

    public void setPointY(int index, double y){
        // проверка индекс за границами
        if (index < 0 || index >= pointslength) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс " + index + " выходит за границы [0, " + (pointslength-1) + "]");
        }

        points_arr[index] = new FunctionPoint(points_arr[index].getX(), y);
    }

    public void deletePoint(int index){
        // проверка индекс за границами
        if (index < 0 || index >= pointslength) {
            throw new FunctionPointIndexOutOfBoundsException("Индекс " + index + " выходит за границы [0, " + (pointslength-1) + "]");
        }

        // проверка минимальное количество точек
        if (pointslength <= 3) {
            throw new IllegalStateException("Нельзя удалить точку - останется меньше 3 точек. Текущее количество: " + pointslength);
        }

        if(index >= 0 && index < pointslength){
            points_arr[index] = null;
            for(int i = index; i < pointslength-1; i++){
                points_arr[i] = points_arr[i+1];
            }
            points_arr[pointslength-1] = null;
            pointslength--;
        }
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        // проверка: дублирование X
        for (int i = 0; i < pointslength; i++) {
            if (compareDouble(points_arr[i].getX(), point.getX())) {
                throw new InappropriateFunctionPointException("Точка с X=" + point.getX() + " уже существует");
            }
        }

        int indx = 0;
        if(point.getX() > points_arr[pointslength-1].getX()){
            indx = pointslength;
        }
        for(int i = 0; i < pointslength-1; i++){
            if(point.getX() >= points_arr[i].getX() && point.getX() <= points_arr[i+1].getX()){
                indx = i+1;
                i=pointslength-1;
            }
        }
        FunctionPoint[] temp_arr = new FunctionPoint[pointslength+1];
        System.arraycopy(points_arr, 0, temp_arr, 0, indx);
        temp_arr[indx] = point;
        System.arraycopy(points_arr, indx, temp_arr, indx+1, pointslength-indx);
        points_arr = temp_arr;
        pointslength++;
    }
}