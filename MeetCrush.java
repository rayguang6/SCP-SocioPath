package s2assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MeetCrush {
    private int size;
    private ArrayList<Integer>[] friendList;
    private ArrayList<ArrayList<Integer>> path = new ArrayList<>();

    public MeetCrush(Group group) {
        this.size = group.size + 1;
        friendList = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            friendList[i] = new ArrayList<>();
        }
        //retrieving all friend edges from the graph and add to ArrayList<Integer>[] friendList
        for (int i = 1; i < size; i++) { 
            ArrayList<Integer> friend = group.getFriends(i);
            for (int f = 0; f < friend.size(); f++) {
                addEdge(i, friend.get(f));
            }
        }
        main();
    }

    public void addEdge(int u, int v) {
        // friendList[source].add(source.friend)
        friendList[u].add(v);
    }

    public void addUndirectedEdge(int u, int v) {
        // friendList[source].add(destination)
        friendList[u].add(v);
        friendList[v].add(u);
    }

    public void printAllPaths(int source, int destination) {
        boolean[] isVisited = new boolean[size];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(source); //list start from source
        printAllPathsUtil(source, destination, isVisited, pathList);
    }

    private void printAllPathsUtil(Integer source, Integer destination, boolean[] isVisited, ArrayList<Integer> localPathList) {
        if (source.equals(destination)) {
            ArrayList<Integer> temp = new ArrayList<>(localPathList);
            path.add(temp);
            //is path found, break loop
            return;
        }
        //the node is discovered
        isVisited[source] = true;
        // From the sourceNode, go find all of his friend and find a possible path to destination
        for (Integer i : friendList[source]) {
            if (!isVisited[i]) {
                localPathList.add(i);
                printAllPathsUtil(i, destination, isVisited, localPathList); //recursive to continue find path from friend's friend to destination
                //after all path from friend 1 is explored, clear friend 1 from the path and add another friend in next round
                localPathList.remove(i);
            }
        }
        // Mark false so the node can be discovered again in next round
        isVisited[source] = false;
    }

    
    public void main() {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        //extra feature to add extra edges (line 74-88)
        System.out.println("Do you want to add more edge? Enter edges you want to add (max 10), 0 if no.");
        int choice = sc.nextInt();
        while (choice > 10) {
            System.out.println("Please enter number of edges again, max 10");
            choice = sc.nextInt();
        }
        for (int i = 0; i < choice; i++) {
            int start = r.nextInt(10) + 1;
            int end;
            do {
                end = r.nextInt(10) + 1;
            } while (friendList[start].contains(end) || start == end);
            System.out.println("Edges added: "+start+" "+end);
            addUndirectedEdge(start, end);
        }
        
        int rumors = r.nextInt(10) + 1;
        int crush = rumors;
        do {
            crush = r.nextInt(10) + 1;
        } while (crush == rumors);

        System.out.println("Following are all different paths from " + rumors + " to " + crush);
        printAllPaths(rumors, crush);
        System.out.println(path);

        int day = 10;
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).size() - 1 < day) {
                day = path.get(i).size() - 1; //get the minimum day
            }
        }
        if (day == 10) { //if path.size is zero
            day = 0;
        }

        ArrayList<Integer> tobeConvinced = new ArrayList<>();
        ArrayList<ArrayList<Integer>> removed = new ArrayList<>();
        //every elements in removed contain 4 elements
        //[the elements to be removed, the min path of that element, the number of min path, the frequency] eg. [[2,3,4,5]]
        int j = 1;
        int dayLeft = day;
        label:
        while (j <= dayLeft) { //starting from day 1 to the min day
            for (int i = 0; i < path.size(); i++) { //loop through all the possible path
                if (path.get(i).get(j) == crush) { //if already arrived crush in that day
                    tobeConvinced.add(path.get(i).get(j - 1));
                    break label;
                }
                boolean found = false;
                for (int z = 0; z < removed.size(); z++) { //to prevent duplicated elements to be removed been added
                    if (removed.isEmpty()) {
                        break;
                    }
                    if (removed.get(z).get(0)== path.get(i).get(j)) {
                        found= true;
                        break;
                    }
                }
                if (!found) { //adding the people who owns rumors on the day with how big influnce is him/her
                    ArrayList<Integer> temp = new ArrayList<>();
                    int this_element =  path.get(i).get(j);
                    int min = 10; //the shortest path, eg. 1 to 3 , [1,2,4,5,3][1,2,4,3][1,2,5,3], shortest path is 4
                    int num_min = 0; //the number of shortest path, ^ number of shortest path is 2
                    int num = 0; //the frequency of thiselement in other possible path
                    for (int t = 0; t < path.size(); t++) {
                        if (path.get(t).contains(this_element)) { //loop all the possible path
                            if (path.get(t).size() < min) {
                                min = path.get(t).size(); //shortest distance
                                num_min=1; 
                            }else if (path.get(t).size() == min) {
                                num_min++; //num of shortest student
                            }
                            num++; //frequency of appearance of this_element in other paths
                        }
                    }
                    temp.add(this_element);
                    temp.add(min);
                    temp.add(num_min);
                    temp.add(num);
                    removed.add(temp);
                }
            }
            //
            int min = 10, min_num = 0, frequency= 0, element = 0;
            for (int i = 0; i < removed.size(); i++) { //loop to find who has greatest influence in the path
                //the priority: people with shortest distance to crush> people with most shortest distance > people with more participance in others line
                
                if (removed.get(i).get(1) < min) {
                    min = removed.get(i).get(1);
                    min_num = removed.get(i).get(2);
                    frequency = removed.get(i).get(3);
                    element = removed.get(i).get(0);
                }else if(removed.get(i).get(1) == min && removed.get(i).get(2) > min_num){
                    min = removed.get(i).get(1);
                    min_num = removed.get(i).get(2);
                    frequency = removed.get(i).get(3);
                    element = removed.get(i).get(0);
                }else if(removed.get(i).get(1) == min && removed.get(i).get(2) == min_num && removed.get(i).get(3)>frequency ){
                    min = removed.get(i).get(1);
                    min_num = removed.get(i).get(2);
                    frequency = removed.get(i).get(3);
                    element = removed.get(i).get(0);
                }
            }
            tobeConvinced.add(element); //convinced the person who brings greater effect
            ArrayList<ArrayList<Integer>> temp = new ArrayList<>(); //temp to store the new path
            for (int p = 0; p < path.size(); p++) {
                if (!path.get(p).contains(element)) { //once the person is convinced, he will stopped every line that he involved in from reaching the crush
                    ArrayList<Integer> temp1 = new ArrayList<>(path.get(p));
                    temp.add(temp1);
                }
            }
            if (temp.isEmpty()) { //all targeted people are convinced
                break label;
            }
            path.clear();//the path will be cleared and stored with new path
            path = temp;
            removed.clear();//restore the elements to be removed
            dayLeft = 10;
            for (int d = 0; d < path.size(); d++) { //update day left to loop (minimum day left in the path)
                if (path.get(d).size() < dayLeft) {
                    dayLeft = path.get(d).size() - 1;
                }
            }
            j++;
        }

        if (day == 1) {
            System.out.println("You cannot convince anyone. ");
        } else if (day == 0) {
            System.out.println("Rumors won't reach your crush. ");
        } else {
            if (dayLeft <= tobeConvinced.size() && !path.isEmpty()) {
                System.out.print("Oops, the following line(s) haven't been broken: " + path + ". You should convince ");
            } else {
                System.out.print("Yes. you can convince ");
            }
            System.out.println(tobeConvinced);
        }
    }
}
