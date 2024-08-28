package SortAlgorithm;

public class MergeSort {
    public static void mergeSort(int[] arr, int l, int r){
        if(l < r){
            int midIndex = (r+l)/2;
            mergeSort(arr, l, midIndex);
            mergeSort(arr, midIndex + 1, r);
            mergeArr(arr, l, midIndex, r);
        }
    }
    //合并数组
    public static void mergeArr(int[] arr, int l, int midIndex, int r){
        //将数组分为左右两个子数组，再按大小排序
        int leftLength = midIndex-l+1;
        int rightLength = r-midIndex;
        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];
        System.arraycopy(arr, l, leftArr, 0, leftLength);
        System.arraycopy(arr, midIndex+1, rightArr, 0, rightLength);
        //遍历、排序
        int i = 0, j = 0;
        int k = l;
        while (i < leftLength && j < rightLength){
            if(leftArr[i] <= rightArr[j]){
                arr[k] = leftArr[i];
                i++;
            }else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        //左子数组全部排序完毕
        while (i < leftLength){
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < rightLength){
            arr[k] = rightArr[j];
            j++;
            k++;
        }

    }

}
