package array;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k],
 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3
 * 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 二分搜索
 *
 * medium
 */
public class A04Search {

    public static void main(String[] args) {
        int[] nums1 = {4,5,6,7,0,1,2};
        System.out.println(new A04Search().search(nums1, 0));
    }

    /**
     * 这启示我们可以在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是
     * 有序的，并根据有序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 target 在不在
     * 这个部分：
     *
     * 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 ([nums[l],nums[mid])，则我们应该将搜索范围缩小至 [l, mid - 1]，
     * 否则在 [mid + 1, r] 中寻找。如果 [mid, r] 是有序数组，且 target 的大小满足 (nums[mid+1],nums[r]]，则我们应该将
     * 搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
     *
     *
     * 复杂度分析
     *
     * 时间复杂度：(logn)，其中 n nums 数组的大小。整个算法时间复杂度即为二分查找的时间复杂度 O(logn)。
     *
     * 空间复杂度： O(1)。我们只需要常数级别的空间存放变量。
     * @param nums
     * @param target
     * @return
     */
    private int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }

        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (target == nums[middle]) {
                return middle;
            }
            if (nums[0] <= nums[middle]) {
                //左半边有序
                if (nums[0] <= target && target <= nums[middle]) {
                    //target值在左半边
                    right = middle -1;
                }else {
                    left = middle + 1;
                }
            } else {
                //右半边有序
                if (nums[middle] <= target && target <= nums[right]) {
                    left = middle + 1;
                }else {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }
}
