//Author:      Nick Seyler
//Date:        December 6, 2015
//Description: A connect-4 game.
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Connect4 extends Application
{
   static Circle[][] cList = new Circle[7][6];
   static Button[] btList = new Button[7];
   static Text text = new Text(0, 0, "RED's turn.  ");
   static boolean redTurn = true;
   
   public void start(Stage primaryStage)
   {
      BorderPane mainPane = new BorderPane();
      GridPane gamePane = new GridPane();
      HBox topPane = new HBox();
      HBox botPane = new HBox();
      botPane.setAlignment(Pos.CENTER);
      
      mainPane.setTop(topPane);
      mainPane.setBottom(botPane);

      text.setFont(Font.font("Calibri", 24));
      
      ResetHandler resetHandler = new ResetHandler();//handles resets
      Button btReset = new Button("Reset"); //creates reset button
      btReset.setOnAction(resetHandler); //resets the game board
      
      //each button is assigned a seperate column handler
      DropHandlerColumn0 dHandler0 = new DropHandlerColumn0();
      Button btDrop0 =  new Button(" Drop ");
      btDrop0.setOnAction(dHandler0);
      btList[0] = btDrop0;
      
      DropHandlerColumn1 dHandler1 = new DropHandlerColumn1();
      Button btDrop1 =  new Button(" Drop ");
      btDrop1.setOnAction(dHandler1);
      btList[1] = btDrop1;
      
      DropHandlerColumn2 dHandler2 = new DropHandlerColumn2();
      Button btDrop2 =  new Button(" Drop ");
      btDrop2.setOnAction(dHandler2);
      btList[2] = btDrop2;
      
      DropHandlerColumn3 dHandler3 = new DropHandlerColumn3();
      Button btDrop3 =  new Button(" Drop ");
      btDrop3.setOnAction(dHandler3);
      btList[3] = btDrop3;
      
      DropHandlerColumn4 dHandler4 = new DropHandlerColumn4();
      Button btDrop4 =  new Button(" Drop ");
      btDrop4.setOnAction(dHandler4);
      btList[4] = btDrop4;
      
      DropHandlerColumn5 dHandler5 = new DropHandlerColumn5();
      Button btDrop5 =  new Button(" Drop ");
      btDrop5.setOnAction(dHandler5);
      btList[5] = btDrop5;
      
      DropHandlerColumn6 dHandler6 = new DropHandlerColumn6();
      Button btDrop6 =  new Button(" Drop ");
      btDrop6.setOnAction(dHandler6);
      btList[6] = btDrop6;
      
      for(int colNum = 0; colNum < 7; colNum ++) //adds circles to array
         for(int rowNum = 0; rowNum < 6; rowNum ++)
         {
            Circle c = new Circle(20);
            c.setFill(Color.WHITE);
            cList[colNum][rowNum] = c;
         }
      
      for(int colNum = 0; colNum < 7; colNum++) //creates the game board
         for (int rowNum = 0; rowNum < 7; rowNum++)
         {
            if (rowNum == 6)
               gamePane.add(btList[colNum], colNum, rowNum); //creates drop buttons
            
            else
            {
               Rectangle r = new Rectangle(50, 50);
               
               StackPane unitPane = new StackPane();
               unitPane.getChildren().add(r); //creates game board outline
               unitPane.getChildren().add(cList[colNum][rowNum]); //creates game board playing field
            
               gamePane.add(unitPane, colNum, rowNum);
            }
         }
      
      topPane.getChildren().add(gamePane);
      botPane.getChildren().addAll(text, btReset);
      
      Scene scene = new Scene(mainPane);
      primaryStage.setTitle("Connect 4");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   
   public static void checkWin(Circle[][] cList, Button[] btList, Text text, boolean redTurn)
   {
      //check for row win
      for(int rowNum = 0; rowNum < 6; rowNum++)
      {
         int count = 0;
         for(int colNum = 0; colNum < 7; colNum++)
         {
            if(redTurn)
            {
               if(cList[colNum][rowNum].getFill().equals(Color.RED))
               {
                  count++;
                  if (count == 4)
                  {
                     text.setText("RED wins!  ");
                     for (int i = 0; i < 7; i++)
                        btList[i].setDisable(true);
                     break;
                  }
               }
               else
                  count = 0;
            }
            else
            {
               if(cList[colNum][rowNum].getFill().equals(Color.YELLOW))
               {
                  count++;
                  if (count == 4)
                  {
                     text.setText("YELLOW wins!  ");
                     for (int i = 0; i < 7; i++)
                        btList[i].setDisable(true);
                     break;
                  }
               }
               else
                  count = 0;
            }  
         }     
      }
      
      //check for column win
      for (int colNum = 0; colNum < 7; colNum ++)
      {
         int count = 0;
         for (int rowNum = 0; rowNum < 6; rowNum ++)
         {
            if(redTurn)
            {
               if(cList[colNum][rowNum].getFill().equals(Color.RED))
               {
                  count++;
                  if (count == 4)
                  {
                     text.setText("RED wins!  ");
                     for (int i = 0; i < 7; i++)
                        btList[i].setDisable(true);
                     break;
                  }
               }
               else
                  count = 0;
            }
            else
            {
               if(cList[colNum][rowNum].getFill().equals(Color.YELLOW))
               {
                  count++;
                  if (count == 4)
                  {
                     text.setText("YELLOW wins!  ");
                     for (int i = 0; i < 7; i++)
                        btList[i].setDisable(true);
                     break;
                  }
               }
               else
                  count = 0;
            }
         }
      }
      
      //check for diagonal win from top left to bottom right
      for (int colNum = 0; colNum < 4; colNum ++)
      {
         for (int rowNum = 0; rowNum < 3; rowNum ++)
         {
            int count = 0;
            for (int i = 0; i < 4; i++)
            {
               if(redTurn)
               {
                  if(cList[colNum + i][rowNum + i].getFill().equals(Color.RED))
                  {
                     count++;
                     if (count == 4)
                     {
                        text.setText("RED wins!  ");
                        for (int j = 0; j < 7; j++)
                           btList[j].setDisable(true);
                        break;
                     }
                  }
                  else
                     count = 0;
               }
               else
               {
                  if(cList[colNum + i][rowNum + i].getFill().equals(Color.YELLOW))
                  {
                     count++;
                     if (count == 4)
                     {
                        text.setText("YELLOW wins!  ");
                        for (int j = 0; j < 7; j++)
                           btList[j].setDisable(true);
                        break;
                     }
                  }
                  else
                     count = 0;
               }
            }   
         }
      }
      //check for diagonal win from top right to bottom left
      for (int colNum = 6; colNum > 2; colNum--)
      {
         for (int rowNum = 0; rowNum < 3; rowNum ++)
         {   
            int count = 0;
            for (int i = 0; i < 4; i ++)
            {
               if(redTurn)
               {
                  if(cList[colNum - i][rowNum + i].getFill().equals(Color.RED))
                  {
                     count++;
                     if (count == 4)
                     {
                        text.setText("RED wins!  ");
                        for (int j = 0; j < 7; j++)
                           btList[j].setDisable(true);
                        break;
                     }
                  }
                  else
                     count = 0;
               }
               else
               {
                  if(cList[colNum - i][rowNum + i].getFill().equals(Color.YELLOW))
                  {
                     count++;
                     if (count == 4)
                     {
                        text.setText("YELLOW wins!  ");
                        for (int j = 0; j < 7; j++)
                           btList[j].setDisable(true);
                        break;
                     }
                  }
                  else
                     count = 0;
               }
            }
         }
      }    
   }
   
   public static Circle[][] getCircleArray()
   {
      return cList;
   }
   
   public static Button[] getButtonArray()
   {
      return btList;
   }
   
   public static Text getInfoText()
   {
      return text;
   }
   
   public static boolean isRedTurn()
   {
      return redTurn;
   }
   
   class ResetHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int colNum = 0; colNum < 7; colNum ++)//makes all circles empty
            for(int rowNum = 0; rowNum < 6; rowNum ++)
            {
               cList[colNum][rowNum].setFill(Color.WHITE);
            }
         
         for(int i = 0; i < 7; i++) //buttons are enabled
            btList[i].setDisable(false);
         
         if (!text.getText().equals("RED's turn.  ")) //text set to reflect turn order
            text.setText("RED's turn.  ");
            
         redTurn = true; //game starts as red
      }
   }
   
   //each button handles a seperate column
   
   class DropHandlerColumn0 implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int i = 5; i >= 0; i--)
            if (cList[0][i].getFill().equals(Color.WHITE))
            {
               if(redTurn)
               {
                  cList[0][i].setFill(Color.RED);
                  redTurn = false;
                  text.setText("YELLOW's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
               else
               {
                  cList[0][i].setFill(Color.YELLOW);
                  redTurn = true; 
                  text.setText("RED's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
            }
            else if (i == 0)
               if (redTurn)
                  text.setText("Column is full. RED's turn.  ");
               else
                  text.setText("Column is full. Yellow's turn.  ");
      }
   }
   
   class DropHandlerColumn1 implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int i = 5; i >= 0; i--)
            if (cList[1][i].getFill().equals(Color.WHITE))
            {
               if(redTurn)
               {
                  cList[1][i].setFill(Color.RED);
                  redTurn = false;
                  text.setText("YELLOW's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
               else
               {
                  cList[1][i].setFill(Color.YELLOW);
                  redTurn = true; 
                  text.setText("RED's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
            }
            else if (i == 0)
               if (redTurn)
                  text.setText("Column is full. RED's turn.  ");
               else
                  text.setText("Column is full. Yellow's turn.  ");
      }
   }
   
   class DropHandlerColumn2 implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int i = 5; i >= 0; i--)
            if (cList[2][i].getFill().equals(Color.WHITE))
            {
               if(redTurn)
               {
                  cList[2][i].setFill(Color.RED);
                  redTurn = false;
                  text.setText("YELLOW's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
               else
               {
                  cList[2][i].setFill(Color.YELLOW);
                  redTurn = true; 
                  text.setText("RED's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
            }
            else if (i == 0)
               if (redTurn)
                  text.setText("Column is full. RED's turn.  ");
               else
                  text.setText("Column is full. Yellow's turn.  ");   
      }
   }
   
   class DropHandlerColumn3 implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int i = 5; i >= 0; i--)
            if (cList[3][i].getFill().equals(Color.WHITE))
            {
               if(redTurn)
               {
                  cList[3][i].setFill(Color.RED);
                  redTurn = false;
                  text.setText("YELLOW's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
               else
               {
                  cList[3][i].setFill(Color.YELLOW);
                  redTurn = true; 
                  text.setText("RED's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
            }
            else if (i == 0)
               if (redTurn)
                  text.setText("Column is full. RED's turn.  ");
               else
                  text.setText("Column is full. Yellow's turn.  ");           
      }
   }
   
   class DropHandlerColumn4 implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int i = 5; i >= 0; i--)
            if (cList[4][i].getFill().equals(Color.WHITE))
            {
               if(redTurn)
               {
                  cList[4][i].setFill(Color.RED);
                  redTurn = false;
                  text.setText("YELLOW's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
               else
               {
                  cList[4][i].setFill(Color.YELLOW);
                  redTurn = true; 
                  text.setText("RED's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
            }
            else if (i == 0)
               if (redTurn)
                  text.setText("Column is full. RED's turn.  ");
               else
                  text.setText("Column is full. Yellow's turn.  ");           
      }
   }
   
   class DropHandlerColumn5 implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int i = 5; i >= 0; i--)
            if (cList[5][i].getFill().equals(Color.WHITE))
            {
               if(redTurn)
               {
                  cList[5][i].setFill(Color.RED);
                  redTurn = false;
                  text.setText("YELLOW's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
               else
               {
                  cList[5][i].setFill(Color.YELLOW);
                  redTurn = true; 
                  text.setText("RED's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
            }
            else if (i == 0)
               if (redTurn)
                  text.setText("Column is full. RED's turn.  ");
               else
                  text.setText("Column is full. Yellow's turn.  ");           
      }
   }
   
   class DropHandlerColumn6 implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent e)
      {
         cList = Connect4.getCircleArray();
         btList = Connect4.getButtonArray();
         text = Connect4.getInfoText();
         redTurn = Connect4.isRedTurn();
         
         for(int i = 5; i >= 0; i--)
            if (cList[6][i].getFill().equals(Color.WHITE))
            {
               if(redTurn)
               {
                  cList[6][i].setFill(Color.RED);
                  redTurn = false;
                  text.setText("YELLOW's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
               else
               {
                  cList[6][i].setFill(Color.YELLOW);
                  redTurn = true; 
                  text.setText("RED's turn.  ");
                  Connect4.checkWin(cList, btList, text, !redTurn);
                  break;
               }
            }
            else if (i == 0)
               if (redTurn)
                  text.setText("Column is full. RED's turn.  ");
               else
                  text.setText("Column is full. Yellow's turn.  ");            
      }
   }
  
   public static void main(String [] args)
   {
      Application.launch(args);
   }
}