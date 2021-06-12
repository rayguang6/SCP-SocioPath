/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

/**
 *
 * @author doublechin
 */
public class ArrangeBook {

    private int size;
    private int[] listOfHeight;
    private int round;

    public ArrangeBook(int size, int[] line) {
        this.size = size;
        this.listOfHeight = line;
        this.round = main();
    }

    public int getRound() {
        return round;
    }

    public int main() {
        MyStack<Integer> stck = new MyStack<>();
        boolean action = true;
        int round = 0;
        while (action) {
            action = false;
            stck.push(listOfHeight[0]);
            /*
            if book arrangement: 13 16 12 17 15, book on shelf: array; book left on shelf after arrangment:stack
            first round: 16 17 taken out, left: 13 12 15 (stack)
            second round: 15 taken out, left: 13 12 (stack)
             */
            for (int j = 1; j < size; j++) {
                if (listOfHeight[j] > listOfHeight[j - 1]) {
                    action = true; //if there is book higher than the previous one, book is taken out.
                } else {
                    stck.push(listOfHeight[j]);//is the book is already lower than previous one, no need take out, put to the stack.
                }
            }
            if (action) {
                round++; //if there is books picked out, mean that action had been done, thus round++
            } else {
                break; //if no action taken out, end the loop
            }
            size = stck.getSize();
            for (int j = size - 1; j >= 0; j--) {
                listOfHeight[j] = stck.pop();
            } //restoring the book in stack into array for next round of arrangement
            System.out.print(round + " .  ");
            for (int j = 0; j < size; j++) {
                System.out.print(listOfHeight[j] + " ");
            }
            System.out.println("");
        }
        return round;
    }
}
