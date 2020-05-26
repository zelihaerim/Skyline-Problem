package assignment_7_zelihaerim_151044065;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import assignment_7_zelihaerim_151044065.Coordinate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {  
	// global fonksiyonlar, main in ustunde tanimli
public static void LookUp(Vector<Vector<Integer>> up,Vector<Coordinate> coo){//sol ustteki noktalarin hangileri etkisiz onlari belirler 
		for (int i = 0; i < coo.size(); i++) {
			for (int k = 0; k < up.size(); k++) {
				if (coo.get(i).getX1() <= up.get(k).get(0) && coo.get(i).getX2() >= up.get(k).get(0)) {
					if (up.get(k).get(1) < coo.get(i).getY() ) {
						Vector<Integer> temp = new Vector<Integer>();
						temp.add(-1); // eger ki yukaridaki noktalar, her hangibir dortgenin x1 ve x2 
						//degerlerinin arasindaysa ve bu degerleri tasiyan  dortgenin
						// y si control edilen noktanin y sinden buyukse dortgenin icinde kalir ve etkisizdir
						temp.add(-1); // etkisisz olmasi o noktanin x ve y indexlerine -1 koyulmasiyla gosterilir,
						//bunlar dahasonra outputa alinmayacaktir
						up.set(k,temp);// -1 yapiyorum remove yaparsam, for''lardan k li dongunun size i degisir
						//ve dongu bazi elemanlara bakmaz ,remove yapmadim yani
					}
				}
			}
		}
	}
	public static void LookDown(Vector<Vector<Integer>> down,Vector<Coordinate> coo) { //sag asagidaki noktalari kontrol eder
		for (int i = 0; i < coo.size(); i++) {
			for (int k = 0; k < down.size(); k++) {
				if (coo.get(i).getX1() < down.get(k).get(0) && coo.get(i).getX2() > down.get(k).get(0)) {
					// eger asagidaki noktanin herhangi bir dortgenin x1 ve x2 si arasindaysa direkt etkisiz zaten 
					// y lerine bakmaya gerek yok.
					Vector<Integer> temp = new Vector<Integer>();
					temp.add(-1); // etkisiz hale getirmek ici -1 koyar
					temp.add(-1);
					down.set(k,temp);
				}
			}
		}
	}
	public static boolean dofind(Vector<Integer>temp,Vector<Vector<Integer>> up) {
		for (int i = 0; i < up.size(); i++) {
			for (int j = 0; j < up.get(i).size(); j++) {
				if (temp.get(0) == up.get(i).get(0) && temp.get(1) == up.get(i).get(1) )
					return true;
			}
		}
		return false;
	}
	public static void fillKoselerdekiNoktalar(Vector<Integer> x1,Vector<Integer> x2,Vector<Integer> y,Vector<Vector<Integer>> leftUp,Vector<Vector<Integer>> rightDown,Vector<Vector<Integer>> rightUp,Vector<Vector<Integer>> leftDown) {
		for (int i = 0; i < x1.size(); i++) {
			Vector<Integer> temp = new Vector<Integer>();
			temp.add(x1.get(i));
			temp.add(y.get(i));
			if (dofind(temp,leftUp) == false)// ayni noktayi iki kere bulundurmasin diye var mi diye kontrol eder false ise yoktur ekler
				leftUp.add(temp);
		}
		for (int i = 0; i < x2.size(); i++) {
			Vector<Integer> temp = new Vector<Integer>();
			temp.add(x2.get(i));
			temp.add(0);
			if (dofind(temp,rightDown) == false)
				rightDown.add(temp);
		}
		for (int i = 0; i < x2.size(); i++) {
			Vector<Integer> temp = new Vector<Integer>();
			temp.add(x2.get(i));
			temp.add(y.get(i));
			if (dofind(temp,rightUp) == false)
				rightUp.add(temp);
		}
		for (int i = 0; i < x1.size(); i++) {
			Vector<Integer> temp = new Vector<Integer>();
			temp.add(x1.get(i));
			temp.add(0);
			if (dofind(temp,leftDown) == false) {
				leftDown.add(temp);
			}
		}
	}
	public static void fillKesisim(Vector<Vector<Integer>> kesisim,Vector<Coordinate> coo) {
		for (int i = 0; i < coo.size() ; i++) {
			for (int j = 0; j < coo.size(); j++) {
				if (coo.get(i).getY() > coo.get(j).getY() ) {                              
					if( coo.get(i).getX1() > coo.get(j).getX1() && coo.get(i).getX2() > coo.get(j).getX1() && coo.get(j).getX2() >coo.get(i).getX1() && coo.get(j).getX2() > coo.get(i).getX2()) {
						Vector<Integer> temp = new Vector<Integer>();
						temp.add(coo.get(i).getX1());
						temp.add(coo.get(j).getY());
						if(dofind(temp,kesisim) == false)
							kesisim.add(temp);
						Vector<Integer> temp1 = new Vector<Integer>();
						temp1.add(coo.get(i).getX2());
						temp1.add(coo.get(j).getY());
						kesisim.add(temp1);				
					}
					else if ( coo.get(i).getX1() < coo.get(j).getX2() && coo.get(i).getX1() > coo.get(j).getX1()) {
						Vector<Integer> temp = new Vector<Integer>();
						temp.add(coo.get(i).getX1());
						temp.add(coo.get(j).getY());
						if(dofind(temp,kesisim) == false)
							kesisim.add(temp);
					}
					else if (coo.get(i).getX2() > coo.get(j).getX1() && coo.get(i).getX2() < coo.get(j).getX2() && coo.get(j).getX1() > coo.get(i).getX1() && coo.get(i).getX1() < coo.get(j).getX2()) {
						Vector<Integer> temp = new Vector<Integer>();
						temp.add(coo.get(i).getX2());
						temp.add(coo.get(j).getY());
						if(dofind(temp,kesisim) == false)
							kesisim.add(temp);
					}
				}
			}
		}
	}
	public static void updateKesisimNoktalari(Vector<Vector<Integer>> kesisim,Vector<Coordinate> coo) {
		for (int i = 0; i < coo.size(); i++) {
			for (int k = 0; k < kesisim.size(); k++) {// kesisim noktalarinin x i bir dortgenin x1 ve x2 arasindaysa
				if (coo.get(i).getX1() < kesisim.get(k).get(0) && coo.get(i).getX2() > kesisim.get(k).get(0)) {
					//ve y si de kucukse dortgenin icinde demektir yani etkisiz hale gelmeli x ve y sine -1 set edilmeli
					if (kesisim.get(k).get(1) < coo.get(i).getY() ) {
						Vector<Integer> temp = new Vector<Integer>();
						temp.add(-1);
						temp.add(-1);
						kesisim.set(k,temp);
					}
				}
			}
		}
	}
	public static int NumOfRow(String fileName,Vector<Integer> width,Vector<Integer> height,Vector<Integer> position) throws FileNotFoundException {
		int counter=0;
		File dosya = new File("data.txt");
		Scanner giris = new Scanner(dosya); 
		while (giris.hasNext()) { // dosyadan alinabilecek deger varsa devam eder yani dosya sonuna kadar
			int sayi1 = giris.nextInt(); // dosyadan integer okur
			width.add(sayi1); // ilk integer width dir
			int sayi2 = giris.nextInt();
			height.add(sayi2); // 2.si height dir
			int sayi3 = giris.nextInt();
			position.add(sayi3); // 3. su position dir
			counter++; // her satir bitisinde counter 1 artara satir sayisini bulmak icin
		}
		giris.close();
		return counter;	
	}// end of NumOfRow
	public static void fillCoo(Vector<Coordinate> coo,Vector<Integer> x1,Vector<Integer> x2,Vector<Integer> y,int size) {
		for (int i = 0; i < size; i++)
			coo.add(new Coordinate());// Coordinate obje vectorunu doldurur
		for (int i = 0; i < x1.size(); i++)
			coo.get(i).setX1(x1.get(i));
		for (int i = 0; i < x2.size(); i++)
			coo.get(i).setX2(x2.get(i));
		for (int i = 0; i < y.size(); i++)
			coo.get(i).setY(y.get(i));
	}
	public static boolean findRightUp(int[] out,Vector<Vector<Integer>> rightUp) { // gonderilen 2 boyutlu array koordinatlari tutuyor
		for (int i = 0; i < rightUp.size(); i++) {// bu koordinatlarin x i ve y si sag ustteki noktalardan birininkiyle ayni mi diye bakar
			if (rightUp.get(i).get(0) == out[0] && rightUp.get(i).get(1) == out[1])
				return true; // gonderilen nokta sag usttekilerden biriyse fonksiyon true dondurur
		}
		return false;
	}
	public static int[][] fillOutput(Vector<Vector<Integer>> Output,Vector<Vector<Integer>> up,Vector<Vector<Integer>> down,Vector<Vector<Integer>> rightUp,Vector<Vector<Integer>> leftDown,Vector<Vector<Integer>> kesisim) {
		for (int i = 0; i < up.size(); i++) { // left up taki noktalar dolduruluyor
			if(up.get(i).get(0) != -1)// egerki x i etkisiz degilse output arrayine alinir
				Output.add(up.get(i));
		}
		for (int i = 0; i < down.size(); i++) { // right down taki noktalar dolduruluyor
			if(down.get(i).get(0) != -1)// egerki x i etkisiz degilse output arrayine alinir
				Output.add(down.get(i));
		}
		for (int i = 0; i < rightUp.size(); i++) {// right up taki noktalar dolduruluyor
			if(rightUp.get(i).get(0) != -1) // egerki x i etkisiz degilse output arrayine alinir 
				Output.add(rightUp.get(i));
		}
		for (int i = 0; i < leftDown.size(); i++) {// left down taki noktalar dolduruluyor
			if(leftDown.get(i).get(0) != -1)// egerki x i etkisiz degilse output arrayine alinir
				Output.add(leftDown.get(i));
		}
		for (int i = 0; i < kesisim.size(); i++) { // dortgenlerin kesistigi noktalar dolduruluyor
			if(kesisim.get(i).get(0) != -1)// egerki x i etkisiz degilse output arrayine alinir
				Output.add(kesisim.get(i));
		}
		int[][] out = new int[Output.size()][2]; // Output Vectoru out arrayine donusturuluyor
		for (int j = 0; j < Output.size(); j++) {
			out[j][0] = Output.get(j).get(0);
			out[j][1] = Output.get(j).get(1); // elemanlar ataniyor
		}
		// array haline donusturmemin amaci asgidaki sort islemlerini yapabilmek icin
		for (int i = 0; i < out.length; i++) {
			for (int j = 0; j < out.length-1-i; j++) { // x e gore kucukten buyuge siraladim
				if (out[j][0] > out[j+1][0]) { // x ler karsilastiriliyor
					int tempX = out[j][0];
					out[j][0] = out[j+1][0];
					out[j+1][0] = tempX;
					int tempY = out[j][1];
					out[j][1] = out[j+1][1];
					out[j+1][1] = tempY;	
				}
			}
		}
		for (int i = 0; i < out.length; i++) {
			for (int j = 0; j < out.length-1-i; j++) { // y lere gore kücükten buyuge siraladim
				if (out[j][0] == out[j+1][0]) {// x leri ayni ise onlar arasinda y ler karsilastirilir ve yer degistirilebilir
					if (out[j][1] > out[j+1][1]) {// y ler karsilastiriliyor
						int tempX = out[j][0];
						out[j][0] = out[j+1][0];
						out[j+1][0] = tempX;
						int tempY = out[j][1];
						out[j][1] = out[j+1][1];
						out[j+1][1] = tempY;
					}
				}
			}
		}// x lere ve y lere gore siralama yaptiktan sonra path tam olarak olustutulamadi cunku eksisim noktalari 
		// ile sag ustteki noktalar arasýnda bazý yerlerde karisiklik olusur
		for (int i = 0; i < out.length; i++) {
			for (int j = 0; j < out.length-1-i; j++) {
				if (out[j][0] == out[j+1][0]) { // x leri ayni olan sag ustteki noktalardan biri ve kesisim noktalarindan biri
					/* ornek 17,9 20,3 20,9 21,3 de sirasi soyle olmali
					 * 17,9 20,9 20,3 21,3 ;
					 * burada out[j+1] bu eleman 20,9 dur out[j][0] bu da 20,3 dir
					 * sag ustte olan 20,9 kesisim noktasi olan 20,3 e gore onceliklidir
					 */
					if (findRightUp(out[j+1],rightUp) == true) { //x leri ayni olan noktalardan, sirad sagda olan nokta ,sagda ve yukaridaki noktalardan biri ise true dir
						//swap
						int tempX = out[j][0]; 
						out[j][0] = out[j+1][0];
						out[j+1][0] = tempX;
						int tempY = out[j][1];
						out[j][1] = out[j+1][1];
						out[j+1][1] = tempY;
					}	
				}
			}
		}
		return out;// output path i olusturuldu.
	}
	public static int[][] callOutput() throws FileNotFoundException{
  	  Vector<Integer> width = new Vector<Integer>(); // tanimlamalar
		Vector<Integer> height = new Vector<Integer>();
		Vector<Integer> position = new Vector<Integer>();
		int satir = NumOfRow("data",width,height,position);
		Vector<Integer> x1 = new Vector<Integer>();
		Vector<Integer> x2 = new Vector<Integer>(); //position + width
		Vector<Integer> y = new Vector<Integer>();
		for (int i = 0; i < position.size(); i++)
			x1.add(position.get(i));	
		for (int i = 0; i < height.size(); i++) {
			if (position.get(i) != 1)
				x2.add(position.get(i)+width.get(i));
			else
				x2.add(width.get(i));
		}
		for (int i = 0; i < height.size(); i++)
			y.add(height.get(i));
		int size=satir;// dosyadan okunan size kadar 
		Vector<Coordinate> coo = new Vector<Coordinate>(size);
		fillCoo(coo,x1,x2,y,size);
		Vector<Vector<Integer>> leftUp = new Vector<Vector<Integer>>();
		Vector<Vector<Integer>> rightDown = new Vector<Vector<Integer>>();
		Vector<Vector<Integer>> rightUp = new Vector<Vector<Integer>>();
		Vector<Vector<Integer>> leftDown = new Vector<Vector<Integer>>();
		Vector<Vector<Integer>> kesisim = new Vector<Vector<Integer>>();
		// dortgenlerin 4 kosesindeki her noktayi alip doldururlar ilgili Vector<Vector<Integer>> degiskenlerine atayip 
		fillKoselerdekiNoktalar(x1,x2,y,leftUp,rightDown,rightUp,leftDown);
		// dortgenlerin icinde kalanlarý vesaire etkissiz hale getirirler Look yapip
		LookUp(leftUp,coo);
		LookUp(rightUp,coo);
		LookDown(rightDown,coo);
		LookDown(leftDown,coo);
		// kesisim noktalarini doldururlar
		fillKesisim(kesisim,coo);
		// kesisim noktalarindan etkisiz olanlarini eler ve kesisim noktalarini update etmis olur
		updateKesisimNoktalari(kesisim,coo);
		Vector<Vector<Integer>> Output = new Vector<Vector<Integer>>();
		int[][] output;
		// outputu doldurur
		output = fillOutput(Output,leftUp,rightDown,rightUp,leftDown,kesisim);
		return output;
  }
	public static int maxOfXAxis(int[][] output) { // outputtaki x lerden en buyugunu bulur garfikte x ekseninin maximum size inin 1 fazlasini belirlemek icin
		int maxX=0;
			for (int j = 0; j < output.length; j++) {
				if (output[j][0]>maxX) {
					maxX=output[j][0];
				}
			}
		return maxX;
	}
    @Override public void start(Stage stage) throws FileNotFoundException { // grafigin cizildigi fonksiyon global oldugu icin
    	// output arrayini oluþturacak tum fonksiyonlar da global olmali
        stage.setTitle("Skyline Problem Grafiði");
        //defining the axes
        // outputu asagida callOutput(); cagirarak doldurdum 
        int[][] output = callOutput(); // callOutput global ve onun icindeki fonksiyonlar da calismasi icin global olmali
        int a = maxOfXAxis(output);// output ta maximum olan X indexini bulur.
        final NumberAxis xAxis = new NumberAxis(0,(a+1),1); // burada x ekseninin size ini belirledim
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("WIDTH"); // x ekseninin ne yi gosterdiginin basligi
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis); // lineChart objesi olusturdum.
        lineChart.setTitle("SKYLINE PROBLEM"); // garfigin basligi
        //defining a series
        XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>(); // garfigin datalarý x ve y leri bu objeye yuklenecek
        series.setName("Corners of the Skyline"); // name i set ettim
       Vector< XYChart.Data<Number,Number> > deneme = new Vector<XYChart.Data<Number,Number> >();
       for (int i = 0; i < output.length; i++) {
			deneme.add(new XYChart.Data<Number,Number>(output[i][0], output[i][1]));// arrayin x ve yleriyle ,Data objelerinin constrlarini cagirarak template XYChart.Data Vectorune ekledim
		}
       for (int i = 0; i < output.length; i++) {
        	 series.getData().add(deneme.get(i)); // series e vector un elemanlarini ekledim
		}	
        for (int i = 0; i < output.length; i++) {
			System.out.print("("+output[i][0]+", "+output[i][1]+")");
			if(i != output.length-1)
				System.out.print(", ");
		}
        Scene scene  = new Scene(lineChart,800,600); // ekrani olusturdu 800x600 lik
        lineChart.getData().add(series); // garifigin datasýna XYChart.Series template clasinin objesini ekledi 
        stage.setScene(scene); // ekraný set ettim
        stage.show();// ekraný gosterecek
    }
    public static void main(String[] args){
    	launch(args); // grafigin cizilmesi icin cagiriyor 
    }// end of main
   
}// end of OrnekMAin class