import java.util.Scanner;




public class Main {
    public static void main(String[] args) {

                System.out.println("Start!");

                boolean start = true;

                int board =  0b000000000000000000;

//        winning combination masks

                int winX1 = 0b000000000000010101;
                int winX2 = 0b000000010101000000;
                int winX3 = 0b010101000000000000;
                int winX4 = 0b000001000001000001;
                int winX5 = 0b000100000100000100;
                int winX6 = 0b010000010000010000;
                int winX7 = 0b010000000100000001;
                int winX8 = 0b000001000100010000;

                int winO1 = 0b000000000000101010;
                int winO2 = 0b000000101010000000;
                int winO3 = 0b101010000000000000;
                int winO4 = 0b000010000010000010;
                int winO5 = 0b001000001000001000;
                int winO6 = 0b100000100000100000;
                int winO7 = 0b100000001000000010;
                int winO8 = 0b000010001000100000;



                Scanner scan = new Scanner(System.in);

                int row, boardField,figure;
                int player= 0;


                while(start){
                    row=0;

//board drawing
                    for(int i = 0; i <3; i++){

                        if(i==1) System.out.println("\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+
                                "\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+
                                "\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500");

                        for(int j = 0; j <3; j++) {
                            if(j==1) System.out.print("\u257F");
                            boardField = ((3<<(row*2))&board)>>(row*2);


                            if(boardField==0) System.out.print("\t  \t");
                            if(boardField==1) System.out.print("\tx\t");
                            if(boardField==2) System.out.print("\to\t");

                            row++;
                            if(j==1) System.out.print("\u257F");
                        }
                        System.out.println();

                        if(i==1) System.out.println("\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+
                                "\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+
                                "\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500"+"\u2500");

                    }


                    if(((board&winX1)==winX1)||((board&winX2)==winX2)||((board&winX3)==winX3)||((board&winX4)==winX4)||
                            ((board&winX5)==winX5)||((board&winX6)==winX6)||((board&winX7)==winX7)||((board&winX8)==winX8)||
                            ((board&winO1)==winO1)||((board&winO2)==winO2)||((board&winO3)==winO3)||((board&winO4)==winO4)||
                            ((board&winO5)==winO5)||((board&winO6)==winO6)||((board&winO7)==winO7)||((board&winO8)==winO8)) {

                        System.out.println("Win - Player nr: "+player);
                        start=false;
                        break;
                    }


                    if(player==1) {
                        player=player+1;
                        figure= 0b10;
                    }
                    else {
                        player=1;
                        figure= 0b01;
                    }

                    System.out.println("Player: " +player);

                    System.out.print("Wirite number of field (Top left - 1, top right 3, bottom right - 9): ");

                    int field = Integer.parseInt(scan.next());
                    if((!(field>=0 && field<=9))||((board>>(2*(field-1)))&3)!=0) {
                        while ((!(field>=0 && field<=9))||((board>>(2*(field-1)))&3)!=0) {
                            if((!(field>=0 && field<=9))) {
                                System.err.println("Number too large - write the correct number");
                                field = Integer.parseInt(scan.next());
                            }
                            if (((board>>(2*(field-1)))&3)!=0){
                                System.err.println("Field taken - write the correct number");
                                field = Integer.parseInt(scan.next());}

                        }
                    }

                    board = (figure<<(2*(field-1)))|board;

                }

            }
        }

