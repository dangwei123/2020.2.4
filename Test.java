
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] res=new int[nums.length];
        int j=0;
        for(int i=0;i<nums.length;i++){
            int left=0;
            int right=j;
            while(left<right){
                int mid=(left+right)>>>1;
                if(res[mid]<nums[i]){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            res[left]=nums[i];
            if(left==j){
                j++;
            }
        }
        return j;
    }
}


class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        Arrays.fill(dp,1,amount+1,amount+1);
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(i>=coins[j]){
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }
}

class Solution {
    public int[] countBits(int num) {
        int[] dp=new int[num+1];
        for(int i=1;i<=num;i++){
            dp[i]=dp[i&(i-1)]+1;
        }
        return dp;
    }
}


class Solution {
    public int integerBreak(int n) {
        if(n<=3){
            return n-1;
        }
        int a=n%3;
        int b=n/3;
        if(a==0){
            return (int)Math.pow(3,b);
        }else if(a==1){
            return (int)Math.pow(3,b-1)*4;
        }else{
            return (int)Math.pow(3,b)*2;
        }
    }
}

class Solution {
    public int integerBreak(int n) {
        int[] dp=new int[n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<i;j++){
                dp[i]=Math.max(dp[i],dp[j]*(i-j));
                dp[i]=Math.max(dp[i],j*(i-j));
            }
        }
        return dp[n];
    }
}

class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if(n==0){
            return 1;
        }
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=10;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+(dp[i-1]-dp[i-2])*(11-i);
        }
        return dp[n];
    }
}


class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int up=1;
        int down=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                up=down+1;
            }else if(nums[i]<nums[i-1]){
                down=up+1;
            }
        }
        return Math.max(up,down);
    }
}

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if(A.length<3){
            return 0;
        }
        int res=0;
        int count=0;
        for(int i=2;i<A.length;i++){
            if(A[i]-A[i-1]==A[i-1]-A[i-2]){
                res+=++count;
            }else{
                count=0;
            }
        }
        return res;
    }
}

class Solution {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for(int i:nums){
            sum+=i;
        }
        if(sum%2!=0){
            return false;
        }
        sum/=2;
        boolean[] dp=new boolean[sum+1];
        dp[0]=true;
        for(int i=0;i<nums.length;i++){
            for(int j=sum;j>=nums[i];j--){
                dp[j]=dp[j]||dp[j-nums[i]];
            }
        }
        return dp[sum];
    }
}

class Solution {
    public int countSubstrings(String s) {
        int sum=0;
        for(int i=0;i<s.length();i++){
            int a=func(i,i,s);
            int b=func(i,i+1,s);
            a=a==1?1:(a+1)/2;
            b=b<2?b:b/2;
            sum+=(a+b);
        }
        return sum;
    }
    private int func(int left,int right,String s){
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
}

class Solution {
    public int longestPalindromeSubseq(String s) {
        int len=s.length();
        int[][] dp=new int[len][len];
        for(int i=len-1;i>=0;i--){
            dp[i][i]=1;
            for(int j=i+1;j<len;j++){
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
}


class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashSet<Integer> set=new HashSet<>();
        int[] sum=new int[nums.length+1];
        int pre=0;
        for(int i=0;i<nums.length;i++){
            sum[i+1]=sum[i]+nums[i];
            int res=k==0?sum[i+1]:sum[i+1]%k;
            if(set.contains(res)){
                return true;
            }
            set.add(pre);
            pre=res;
        }
        return false;
    }
}