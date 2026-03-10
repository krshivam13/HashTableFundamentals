import java.util.*;

class FraudDetection {

    public static List<int[]> findTwoSum(int[] arr, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (int num : arr) {

            int complement = target - num;

            if (map.containsKey(complement)) {

                result.add(new int[]{num, complement});
            }

            map.put(num, 1);
        }

        return result;
    }
}