package array;

import java.util.Random;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 快速选择算法
 * 堆算法
 *
 * medium
 */
public class A01FindKthLargest {

    public static void main(String[] args) {
        int[] nums = {1, 3,5,2,1,4};
        System.out.println(new A01FindKthLargest().findKthLargest(nums, 2));
    }


    /**
     * 方法一：基于快速排序的选择方法
     * 思路和算法
     *
     * 我们可以用快速排序来解决这个问题，先对原数组排序，再返回倒数第 k个位置，这样平均时间复杂度是O(nlogn)，但其实我们可以
     * 做的更快。
     *
     * 首先我们来回顾一下快速排序，这是一个典型的分治算法。我们对数组a[l⋯r] 做快速排序的过程是（参考《算法导论》）：
     *
     * 分解： 将数组a[l⋯r] 「划分」成两个子数组a[l⋯q−1]、a[q+1⋯r]，使得a[l⋯q−1] 中的每个元素小于等于 a[q]，且 a[q] 小于
     * 等于a[q+1⋯r] 中的每个元素。其中，计算下标 q 也是「划分」过程的一部分。
     * 解决： 通过递归调用快速排序，对子数组[l⋯q−1] 和a[q+1⋯r] 进行排序。
     * 合并： 因为子数组都是原址排序的，所以不需要进行合并操作，a[l⋯r] 已经有序。
     * 上文中提到的 「划分」 过程是：从子数组 a[l⋯r] 中选择任意一个元素 x作为主元，调整子数组的元素使得左边的元素都小于
     * 等于它，右边的元素都大于等于它， x 的最终位置就是 q。
     * 由此可以发现每次经过「划分」操作后，我们一定可以确定一个元素的最终位置，即 x 的最终位置为 q，并且保证a[l⋯q−1] 中的
     * 每个元素小于等于 a[q]，且 a[q] 小于等于 a[q+1⋯r] 中的每个元素。所以只要某次划分的 q 为倒数第 k 个下标的时候，我们
     * 就已经找到了答案。 我们只关心这一点，至于 a[l⋯q−1] 和 a[q+1⋯r] 是否是有序的，我们不关心。
     *
     * 因此我们可以改进快速排序算法来解决这个问题：在分解的过程当中，我们会对子数组进行划分，如果划分得到的 q 正好就是我们
     * 需要的下标，就直接返回 a[q]；否则，如果 q 比目标下标小，就递归右子区间，否则递归左子区间。这样就可以把原来递归两个
     * 区间变成只递归一个区间，提高了时间效率。这就是「快速选择」算法。
     *
     * 我们知道快速排序的性能和「划分」出的子数组的长度密切相关。直观地理解如果每次规模为 n 的问题我们都划分成 1 和 n−1，
     * 每次递归的时候又向 n−1 的集合中递归，这种情况是最坏的，时间代价是 O(n ^ 2)。我们可以引入随机化来加速这个过程，它的
     * 时间代价的期望是 O(n)，证明过程可以参考「《算法导论》9.2：期望为线性的选择算法」
     *
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，如上文所述，证明过程可以参考「《算法导论》9.2：期望为线性的选择算法」。
     * 空间复杂度：O(logn)，递归使用栈空间的空间代价的期望为 O(logn)。
     */
    int [] nums;

    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        int length = nums.length;
        return quickSelect(0, length - 1, length - k);
    }

    public int quickSelect(int left, int right, int k_smallest) {
        //递归终止条件
        if (left == right) {
            return this.nums[left];
        }

        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);
        pivot_index = partition(left, right, pivot_index);

        if (k_smallest == pivot_index) {
            return this.nums[k_smallest];
        } else if (k_smallest < pivot_index) {
            return quickSelect(left, pivot_index - 1, k_smallest);
        } else {
            return quickSelect(pivot_index + 1, right, k_smallest);
        }
    }

    /**
     * 将轴值大的元素放在右边，小的放在左边
     * @return
     */
    public int partition(int left, int right, int pivot_index) {
        int pivot = this.nums[pivot_index];
        //将轴值放置最右边
        swap(pivot_index, right);
        //初始化轴值存储的下标
        int store_index = left;

        //遍历数组调整轴值位置
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                //swap(store_index, i);
                store_index ++;
            }
        }

        //把轴值放在预期位置
        swap(store_index, right);

        //返回轴值元素的下标
        return store_index;
    }

    public void swap(int a, int b) {
        int temp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = temp;
    }

    /**
     * 堆排序
     *
     * 复杂度分析
     *
     * 时间复杂度：O(nlogn)，建堆的时间代价是 O(n)，删除的总代价是 O(klogn)，因为 k<n，故渐进时间复杂为 O(n+klogn)=O(nlogn)。
     * 空间复杂度：O(1)，
     * @param nums
     * @param k
     * @return
     */
/*    private int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        int length = nums.length;

        return this.heapSort(length, k);
    }

    private int heapSort(int length, int k){
        int parent = length/2 -1;

        for (int i = parent; i >=0; i--) {
            this.heapAdjust(i, length);
        }

        for (int i = length -1; i > 0; i--) {
            swap(0, i);
            heapAdjust(0, i);
        }

        return this.nums[length - k];

    }

    private void heapAdjust(int parent, int length){
        int tmp = this.nums[parent];
        int leftChild = 2 * parent + 1;
        while (leftChild < length) {
            if (leftChild + 1 < length && this.nums[leftChild] < this.nums[leftChild + 1]) {
                leftChild ++;
            }

            if (tmp >= this.nums[leftChild]) {
                break;
            }

            this.nums[parent] = this.nums[leftChild];

            parent = leftChild;

            leftChild = 2 * leftChild + 1;
        }
        this.nums[parent] = tmp;
    }

    private void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }*/
}
