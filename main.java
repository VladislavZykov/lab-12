import java.util.Arrays;

public class main {
    static final int size = 4;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr = new float[size];          // 1) Создают одномерный длинный массив, например:
//      float[] arrM1 = new float[size];
//      float[] arrM2 = new float[size];
//      float[] arrM3 = new float[size];

        fillArray(arr);
        long time = System.currentTimeMillis(); // 3) Засекают время выполнения: long a = System.currentTimeMillis();
        System.out.println("method1 before " + Arrays.toString(arr));
        method1(arr);
        // 5) Проверяется время окончания метода System.currentTimeMillis();
        // 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
        System.out.println("execution time " + (System.currentTimeMillis() - time));
//      System.arraycopy(arr, 0, arrM1, 0, size);
        System.out.println("method1 after " + Arrays.toString(arr));
//      System.out.println(Arrays.toString(arrM1));

        fillArray(arr);
        System.out.println();
        System.out.println("method2 before " + Arrays.toString(arr));
        time = System.currentTimeMillis();
        method2(arr);
        // 5) Проверяется время окончания метода System.currentTimeMillis();
        // 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
        System.out.println("execution time " + (System.currentTimeMillis() - time));
        System.out.println("method2 after " + Arrays.toString(arr));

//      System.arraycopy(arr, 0, arrM2, 0, size);
//      System.out.println(Arrays.toString(arr));
//      System.out.println(Arrays.toString(arrM2));
//      System.arraycopy(arr, 0, arrM3, 0, size);
//      arrM3[0] = 1;
//      System.out.println("Массивы 1 и 2 равны: " + compareArrays(arr, arrM2));
//      System.out.println("Массивы 1 и 3 равны: " + compareArrays(arr, arrM3));
    }

    //  2) Заполняют этот массив единицами;
    private static void fillArray(float a[]) {
        for (int i = 0; i < a.length; i++)
            a[i] = 1;
    }

    //  4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
    private static void method1(float a[]) {
        for (int i = 0; i < a.length; i++)
            a[i] = (float) (a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    private static void method2a(float a[], int h) {
//  private static void method2a(float a[]) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (float) (a[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5)
                    * Math.cos(0.4f + (i + h) / 2));
//          a[i] = (float) (a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
//                  * Math.cos(0.4f + i / 2));
//          try {
//              Thread.sleep(1 / 100);
//          } catch (InterruptedException e) {
//              e.printStackTrace();
//          }
        }
    }

    private static void method2(float a[]) {
//      J2HW5 m2 = new J2HW5();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(a, 0, a1, 0, h);
        System.arraycopy(a, h, a2, 0, h);

        System.out.println("a1 before " + Arrays.toString(a1));
        System.out.println("a2 before " + Arrays.toString(a2));

        new Thread(() -> main.method2a(a1, 0)).start();
        new Thread(() -> main.method2a(a2, h)).start();
//      new Thread(() -> J2HW5.method2a(a1)).start();
//      new Thread(() -> J2HW5.method2a(a2)).start();

        System.out.println("a1 after " + Arrays.toString(a1));
        System.out.println("a2 after " + Arrays.toString(a2));

        System.arraycopy(a1, 0, a, 0, h);
        System.arraycopy(a2, 0, a, h, h);
    }

    private static boolean compareArrays(float a1[], float a2[]) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i])
                return false;
        }
        return true;
    }

}