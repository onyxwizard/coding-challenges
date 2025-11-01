package SlidingWindow.Arrays.Fixed.Medium.GrumpyBookStore;

public class GrumpyBookStore {
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int maxSatisfiedCustomer = 0;
        for(int i =0; i<customers.length;i++){
            if(grumpy[i] == 0){
                maxSatisfiedCustomer+= customers[i];
            }
        }


        int customerSatisfied = maxSatisfiedCustomer;
        for(int i=0;i<minutes;i++){
            if(grumpy[i]==1){
                customerSatisfied += customers[i];
            }
        }
        maxSatisfiedCustomer = Math.max(customerSatisfied,maxSatisfiedCustomer);


        for(int i=minutes;i<customers.length;i++){
            int oldIdx = i-minutes;
            int newIdx = i;
            if(grumpy[oldIdx] == 1){
                customerSatisfied -= customers[oldIdx];
            }
            if(grumpy[newIdx] == 1) {
                customerSatisfied += customers[newIdx];
            }
            maxSatisfiedCustomer = Math.max(customerSatisfied,maxSatisfiedCustomer);
        }

        return maxSatisfiedCustomer;
    }

    public static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5}, grumpy = {0,1,0,1,0,1,0,1};
        int minutes = 3;
        int res = maxSatisfied(customers,grumpy,minutes);
        System.out.println(res);

        int[] customerss = {4,10,10}, grumpys = {1,1,0};
        minutes = 2;
        res = maxSatisfied(customerss,grumpys,minutes);
        System.out.println(res);
    }
}
