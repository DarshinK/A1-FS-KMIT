/*
You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.

Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip. 
Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.

You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. 
Return the minimum time required for all buses to complete at least totalTrips trips.

Input Format:
-------------
Line-1: An integer n, size of time
Line-2: n space separated integers, time[i]
Line-3: An integer, totalTrips

Sample Input-1:
---------------
3
1 2 3
5

Sample Output-1: 
----------------
3
 
Explanation:
-------------
-> At time t = 1, the number of trips completed by each bus are [1,0,0]. 
  The total number of trips completed is 1 + 0 + 0 = 1.
-> At time t = 2, the number of trips completed by each bus are [2,1,0]. 
  The total number of trips completed is 2 + 1 + 0 = 3.
-> At time t = 3, the number of trips completed by each bus are [3,1,1]. 
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.

Sample Input-2:
---------------
1
2
1

Sample Output-2: 
----------------
2


Explanation:
--------------
There is only one bus, and it will complete its first trip at t = 2.
So the minimum time needed to complete 1 trip is 2.
 

Constraints:
------------
1 <= time.length <= 10^5
1 <= time[i], totalTrips <= 10^7

 */
import java.util.*;
public class BinaryTrips {
    private static int getTime(int[] time,int curr_time){
        int trips = 0;

        for(int t : time){
            trips += (curr_time / t);
        }

        return trips;
    }
    private static int solve(int[] time,int totalTrips){
        int minTime = Arrays.stream(time).min().getAsInt() ;
        int maxTime = minTime * totalTrips ;

        for(int t = minTime ; t <= maxTime ; t++){
            int curr_trips = getTime(time,t);

            if(curr_trips >= totalTrips){
                return t;
            }
        }

        return 0;
    }

    private static int binarySearch(int[] time,int totalTrips){
        int left = Arrays.stream(time).min().getAsInt() ;
        int right = left * totalTrips ;

        while(left <= right){
            int mid = left + (right - left)/2;

            int curr_trips = getTime(time,mid);

            if(curr_trips >= totalTrips){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] time = new int[n];
        for(int i = 0 ; i < n ; i++){
            time[i] = sc.nextInt();   
        }
        int totalTrips = sc.nextInt();

        System.out.println(binarySearch(time,totalTrips));
        
        
        sc.close();
    }
}
