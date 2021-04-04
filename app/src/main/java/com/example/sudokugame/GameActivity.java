package com.example.sudokugame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.BLUE;

public class GameActivity extends AppCompatActivity {
    private String TAG = "GameActivity";

    private CharSequence[] easyPuzzles = {
            // list of 50 'easy' sudoku puzzle starting states
            "003020600900305001001806400008102900700000008006708200002609500800203009005010300",
            "200080300060070084030500209000105408000000000402706000301007040720040060004010003",
            "000000907000420180000705026100904000050000040000507009920108000034059000507000000",
            "030050040008010500460000012070502080000603000040109030250000098001020600080060020",
            "020810740700003100090002805009040087400208003160030200302700060005600008076051090",
            "100920000524010000000000070050008102000000000402700090060000000000030945000071006",
            "043080250600000000000001094900004070000608000010200003820500000000000005034090710",
            "480006902002008001900370060840010200003704100001060049020085007700900600609200018",
            "000900002050123400030000160908000000070000090000000205091000050007439020400007000",
            "001900003900700160030005007050000009004302600200000070600100030042007006500006800",
            "000125400008400000420800000030000095060902010510000060000003049000007200001298000",
            "062340750100005600570000040000094800400000006005830000030000091006400007059083260",
            "300000000005009000200504000020000700160000058704310600000890100000067080000005437",
            "630000000000500008005674000000020000003401020000000345000007004080300902947100080",
            "000020040008035000000070602031046970200000000000501203049000730000000010800004000",
            "361025900080960010400000057008000471000603000259000800740000005020018060005470329",
            "050807020600010090702540006070020301504000908103080070900076205060090003080103040",
            "080005000000003457000070809060400903007010500408007020901020000842300000000100080",
            "003502900000040000106000305900251008070408030800763001308000104000020000005104800",
            "000000000009805100051907420290401065000000000140508093026709580005103600000000000",
            "020030090000907000900208005004806500607000208003102900800605007000309000030020050",
            "005000006070009020000500107804150000000803000000092805907006000030400010200000600",
            "040000050001943600009000300600050002103000506800020007005000200002436700030000040",
            "004000000000030002390700080400009001209801307600200008010008053900040000000000800",
            "360020089000361000000000000803000602400603007607000108000000000000418000970030014",
            "500400060009000800640020000000001008208000501700500000000090084003000600060003002",
            "007256400400000005010030060000508000008060200000107000030070090200000004006312700",
            "000000000079050180800000007007306800450708096003502700700000005016030420000000000",
            "030000080009000500007509200700105008020090030900402001004207100002000800070000090",
            "200170603050000100000006079000040700000801000009050000310400000005000060906037002"
    };
    private CharSequence[] mediumPuzzle = {
            "916004072800620050500008930060000200000207000005000090097800003080076009450100687",
            "000900082063001409908000000000670300046050290007023000000000701704300620630007000",
            "035670000400829500080003060020005807800206005301700020040900070002487006000052490",
            "030070902470009000009003060024000837007000100351000620040900200000400056708050090",
            "084200000930840000057000000600401700400070002005602009000000980000028047000003210",
            "007861000008003000560090010100070085000345000630010007050020098000600500000537100",
            "040001003000050079560002804100270080082000960030018007306100098470080000800500040",
            "000500006000870302270300081000034900793050614008790000920003057506087000300005000",
            "000900067090000208460078000320094070700603002010780043000850016501000090670009000",
            "024000017000301000300000965201000650000637000093000708539000001000502000840000570",
            "200006143004000600607008029100800200003090800005003001830500902006000400942600005",
            "504002030900073008670000020000030780005709200047060000050000014100450009060300502",
            "580000637000000000603540000090104705010709040807205090000026304000000000468000072",
            "000010000900003408670500021000130780015000240047065000750006014102400009000090000",
            "780300050956000000002065001003400570600000003025008100200590800000000417030004025",
            "200367500500800060300450700090530400080000070003074050001026005030005007002783001",
            "801056200000002381900003000350470000008000100000068037000600002687100000004530806",
            "300004005841753060000010000003000087098107540750000100000070000030281796200300008",
            "000064810040050062009010300003040607008107500704030100006070200430080090017390000",
            "000040320000357080000600400357006040600705003080900675008009000090581000064070000",
            "905040026026050900030600050350000009009020800100000075010009030003080760560070108",
            "010403060030017400200000300070080004092354780500070030003000005008530040050906020",
            "605900100000100073071300005009010004046293510700040600200001730160002000008009401",
            "049060002800210490100040000000035084008102300630470000000080001084051006700020950",
            "067020300003700000920103000402035060300000002010240903000508039000009200008010750",
            "050842001004000900800050040600400019007506800430009002080090006001000400500681090",
            "000076189000002030009813000025000010083000590070000460000365200010700000536120000",
            "080000030400368000350409700000003650003000900078100000004201076000856009060000020",
            "000500748589000001700086900302010580000000000067050204004760002200000867876005000",
            "021009008000004031740100025000007086058000170160800000910008052230900000800300410"
    };
    private CharSequence[] hardPuzzle = {
            "600300100071620000805001000500870901009000600407069008000200807000086410008003002",
            "906013008058000090030000010060800920003409100049006030090000080010000670400960301",
            "300060250000500103005210486000380500030000040002045000413052700807004000056070004",
            "060001907100007230080000406018002004070040090900100780607000040051600009809300020",
            "600300208400185000000000450000070835030508020958010000069000000000631002304009006",
            "400030090200001600760800001500318000032000510000592008900003045001700006040020003",
            "004090170900070002007204000043000050798406213060000890000709400600040001085030700",
            "680001003007004000000820000870009204040302080203400096000036000000500400700200065",
            "000002000103400005200050401340005090807000304090300017605030009400008702000100000",
            "050702003073480005000050400040000200027090350006000010005030000400068730700109060",
            "500080020007502801002900040024000308000324000306000470090006700703208900060090005",
            "108090000200308096090000400406009030010205060080600201001000040360904007000060305",
            "010008570607050009052170000001003706070000040803700900000017260100020407024300090",
            "020439800080000001003001520050092703000000000309740080071300900600000030008924010",
            "000500201800006005005207080017960804000000000908074610080405300700600009504009000",
            "920000000500870000038091000052930160090000030073064980000410250000053001000000073",
            "590006010001254709000001400003715008100000004200648100002500000708463900050100047",
            "309870004000005008870400000104580003000706000700034105000009081900300000400057206",
            "800200000910300706000007002084000009095104860100000230500600000609003071000005008",
            "005037001000050627600002530020070000001968200000010090013700008486090000700840100",
            "090350700000800029000402008710000000463508297000000051300204000940005000008037040",
            "000005904080090605006000030030701450008040700074206090060000300801060070309800000",
            "030004087948700500060800009010586720000000000087312050800003070003007865570200090",
            "300687015000030082050000300400300000601050709000004003008000020210040000970521004",
            "702000004030702010400093008000827090007030800080956000300570009020309080600000503",
            "300040057400853060025700000000000430800406001034000000000005690090624003160080002",
            "000260050000005900000380046020094018004000500950810070380021000005700000040058000",
            "062080504008050090700320001000740620000203000027065000200036007040070100803090240",
            "002001000068000003000086090900002086804000102520800009080140000100000920000700500",
            "000030065460950200000086004003070006004090100500010300200140000007065028630020000"
    };
    private RadioGroup rg1, rg2, rg3;
    private int puzzle;
    private List<SudokuCell> cells = new ArrayList<>();

    Random r = new Random(); //random sınıfı
    int puzzleNum = r.nextInt(31);

    public GameActivity() {
    }

    // dptopx that accepts a floating point value input and context outputs an int with units of pixels
    private int dpToPx(float dp, Context context) {
        float screenDensity = getResources().getDisplayMetrics().density;
        long pixels = Math.round(dp * screenDensity); // rounds up/down around 0.5
        //TODO: can change to this one line instead...?
//        int pix = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
        return (int) pixels;
    }


    @Nullable
    private CustomRadioButton checkRBs(CustomRadioButton[] radioButtons) {
        // iterate through array to check if any button is checked, then return that item, else return null
        for (CustomRadioButton element : radioButtons) {
            if (element.isChecked()) {
                return element;
            }
        }
        return null;
    }

    private ArrayList<ArrayList<SudokuCell>> rowValues;
    private ArrayList<ArrayList<SudokuCell>> colValues;
    private ArrayList<ArrayList<SudokuCell>> blockValues;

    private void createLists() {
        rowValues = new ArrayList<>();
        colValues = new ArrayList<>();
        blockValues = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            rowValues.add(new ArrayList<SudokuCell>());
            colValues.add(new ArrayList<SudokuCell>());
            blockValues.add(new ArrayList<SudokuCell>());
        }
    }

    private boolean checkList(ArrayList<ArrayList<SudokuCell>> numList) {
        // checks all groups of a certain type (row/col/block) to see if all integers, 1-9, are present in each
        // returns false if an integer is not found exactly once in a group (not found or more than one found)

        String[] numbers = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

        // check each group of 9 cells
        for (ArrayList<SudokuCell> array : numList) {
            for (String num : numbers) {  // check groups each have integers 1-9
                int check = 0;  // initialise counter to identify errors
                for (int i = 0; i < 9; i++) {  // check each cell in the group for the integer
                    SudokuCell cell = array.get(i);
                    if (cell.getValue().equals(num)) {  // if integer is found in cell increment counter
                        check += 1;
                    }  // else continue to check values
                }
                if (check != 1) {  // if more/less than 1 of an integer is found in a group, then the grid contains error(s)
                    return false;
                }  // else continue checking
            }
        }
        return true;  // return true if all checks are passed - is a potentially correct solution if other groups pass
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final GridLayout gridLayout = findViewById(R.id.gridLayout);

        final ImageButton checkButton = findViewById(R.id.checkButton2);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if all rows/columns/blocks have errors
                //TODO: order of these could matter? what group more likely to have errors -> should be first
                if (checkList(rowValues) || checkList(colValues) || checkList(blockValues)) {  // correct solution
                    Toast gameWin = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG);
                    gameWin.show();
                } else {  // incorrect solution
                    Toast gridErrors = Toast.makeText(getApplicationContext(), "Check for Errors.", Toast.LENGTH_SHORT);
                    gridErrors.show();
                }
            }
        });

        // radiogroups and listeners
        rg1 = findViewById(R.id.RadioGroup1);
     /*   rg2 = findViewById(R.id.RadioGroup2);
        rg3 = findViewById(R.id.RadioGroup3);*/
       /* rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);
        rg3.setOnCheckedChangeListener(listener3);*/
        // radiobuttons
        CustomRadioButton rb1 = findViewById(R.id.Set1);
        CustomRadioButton rb2 = findViewById(R.id.Set2);
        CustomRadioButton rb3 = findViewById(R.id.Set3);
        CustomRadioButton rb4 = findViewById(R.id.Set4);
        CustomRadioButton rb5 = findViewById(R.id.Set5);
        CustomRadioButton rb6 = findViewById(R.id.Set6);
        CustomRadioButton rb7 = findViewById(R.id.Set7);
        CustomRadioButton rb8 = findViewById(R.id.Set8);
        CustomRadioButton rb9 = findViewById(R.id.Set9);
        // array of radiobuttons used for checking active button across all 3 groups at once
        final CustomRadioButton[] radioButtons = {
                rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9
        };
        // pencil and eraser buttons
        final ImageButton pencilButton = findViewById(R.id.pencilButton2);
        final ImageButton eraserButton = findViewById(R.id.eraserButton2);

        // turn off eraser button if radio button is clicked
        for (final CustomRadioButton element : radioButtons) {
            element.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eraserButton.setSelected(false);

                }
            });
        }


        // turn off radio buttons if eraser is clicked
        eraserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected((!v.isSelected()));
                if (v.isSelected()) {
                    eraserButton.setSelected(true);
                    rg1.clearCheck();
                    //rg2.clearCheck();
                    // rg3.clearCheck();
                } else {
                    eraserButton.setSelected(false);
                }

            }
        });
        //Notları aktif etme ve ya iftal etme
        pencilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if (v.isSelected()) {
                    pencilButton.setSelected(true);
                } else {
                    pencilButton.setSelected(false);
                }
            }
        });


        // ızgara sütun kenar boşlukları için dp'yi piksele dönüştür - döngü dışında kullanıldı, bu nedenle yalnızca 1 çağrı gerekli
        // each cell has 1dp border, so middle margins must be 2dp and edge margins 3dp to total 4dp thick grid borders

        final int margin2dp = dpToPx(2, this);
        final int margin3dp = dpToPx(3, this);
        // margin between grid and edge of screen// ızgara ve ekranın kenarı arasındaki kener boşluğu
        final int margin4dp = dpToPx(4, this);
        int wrap = ViewGroup.LayoutParams.WRAP_CONTENT;

        ViewGroup.LayoutParams gridMargins = gridLayout.getLayoutParams();
        gridMargins.height = wrap;
        gridMargins.width = wrap;
        ((ViewGroup.MarginLayoutParams) gridMargins).setMargins(margin4dp, margin4dp, margin4dp, 0);

        // determine largest cell size possible given the devices width (pixels)
        float screenSize = getResources().getDisplayMetrics().widthPixels;
        // need to account for margins associated with the grid and cells
        int marginsPx = (margin2dp + margin3dp + margin4dp) * 2;
        // rounds down to nearest integer, as we want a whole number less than the maximum
        // 4dp x 2 margins between side of grid and screen, 3dp x 2 + 2dp x 2 for margins between grid blocks = 18dp
        int maxCellSize = (int) (screenSize - marginsPx) / 9;
        ViewGroup.LayoutParams layoutSize = new ViewGroup.LayoutParams(maxCellSize, maxCellSize);

        // get the puzzle number to set associated starting values


        //int puzzle = getIntent().getIntExtra("puzzle number", 1) - 1;

        // System.out.println(denemee);


        //rastgele sayı dizi sayısını seçiyor

        // create lists for cells to be added to upon creation
        createLists();

        // insert 9 cells into each row in the grid
        //her ssatıra 9 hücre ekliyor
        for (int i = 0; i < 9; i++) {
            for (int h = 0; h < 9; h++) {
                int index = i * 9 + h;
                // Log.i("gridLayoutTest", "index: " + index);


                // value of 0 means no starting value
                // 0 değeri, başlangıç ​​değeri olmadığı anlamına gelir
                final SudokuCell sudokuCell = new SudokuCell(this);

                // add cell object to appropriate lists for future access
                rowValues.get(i).add(sudokuCell);
                colValues.get(h).add(sudokuCell);

                // blocks are in a 3x3 grid which overlap multiple rows and columns...
                // cell row/col index -> block row/col index: (0,1,2)-> 0 | (3,4,5)-> 1 | (6,7,8)-> 2
                double xBlockIndex = Math.floor(h / 3f);
                double yBlockIndex = Math.floor(i / 3f);
                int blockIndex = (int) (xBlockIndex + 3 * yBlockIndex);  // block index = 0-8 from left-right, top-bottom
                blockValues.get(blockIndex).add(sudokuCell);

                // set cells size to 37 x 37 dp
                // hücre boyutunu 37 x 37 dp olarak ayarla
                sudokuCell.setLayoutParams(layoutSize);
                puzzle = puzzleNum;
                char StartValue = 0;
                String denemee = getIntent().getExtras().getString("deneme");
                if (denemee.equals(R.string.difficulty_easy)) {
                    StartValue = easyPuzzles[puzzle].charAt(index);

                } else if (denemee.equals(R.string.difficulty_medium)) {
                    StartValue = mediumPuzzle[puzzle].charAt(index);

                } else  {
                    StartValue = hardPuzzle[puzzle].charAt(index);
                }


                // if the top 'Puzzle#' selection was set give a warning to choose a puzzle
                System.out.println("random gelen" + puzzle);


                // set starting values for cells

                //  char StartValue = easyPuzzles[puzzle].charAt(index);
                // set starting values and add cells to grid
                // başlangıç ​​değerlerini ayarlayın ve hücreleri ızgaraya ekleyin
                if (StartValue != '0') {
                    sudokuCell.setValue(StartValue + "");
                    sudokuCell.setTextColor(Color.BLACK);// Sudokunun puzzledan gelen sayılar  siyah renginde geliyor
//                    sudokuCell.setNotEditable();

                    gridLayout.addView(sudokuCell, index);


                } else {  // editable values with no starting value
                    //TODO: create one onclicklistener and use for all buttons instead
                    //  need class variables for eraserbutton/pencilbutton/RBs so the method can access them
                    //  - or put in SudokuCell class and make a variable in this class that tells what button state active
                    //  can access by passing context to cell and then use in onclicklistener that is set on instantiation

                    // set a new onclicklistener for each of the changeable cell buttons
                    sudokuCell.setOnClickListener(new View.OnClickListener() {

                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
//                            if(!eraserButton.isChecked() && checkRBs(radioButtons) == null && !pencilButton.isChecked()) {
//                                //no buttons checked - do something?
//                            }
                            //TODO: BUG - PLACED VALUE THEN PENCIL AND BOTH WERE SHOWN AT SAME TIME. UNSURE HOW TO RECREATE
                            //  ONLY HAPPENED AFTER ADDING WHITE BACKGROUND EACH REDRAW (I THINK) - CHECK CODE HERE

                            // if eraser is active no radiobuttons can be active, so process eraser clicks first
                            String emptybutton = "";
                            if (eraserButton.isSelected()) {
                                if (pencilButton.isSelected()) {
                                    // erase all pencil values
                                    sudokuCell.clearPencilValues();
                                } else {
                                    // erase cell value and show pencil values
                                    //  hücre değerini sil ve kalem değerlerini göster
                                    sudokuCell.setValue(emptybutton);
                                }
                            } // if eraser inactive then we can move on to check if a radio button is active


                            /// Burada kaldın burada bir şeyler var
                            CustomRadioButton activeRB = checkRBs(radioButtons);
                            if (activeRB != null && !activeRB.isSelected()) {  // only proceed if a radiobutton is on
                                int pos = Arrays.asList(radioButtons).indexOf(activeRB) + 1;  // 1-9 - index is 1 less
                                if (!pencilButton.isSelected()) {
                                    System.out.println("buradayım1");
                                    // if pencil button is not on...change cell value to corresponding integer
                                    String integerString = Integer.toString(pos);
                                    Log.i(TAG, "RB string: " + integerString);
                                    // if cellbutton contains that integer already remove it
                                    //yazdığı değeri siliyor
                                    if (sudokuCell.getValue().equals(integerString)) {
                                        sudokuCell.setValue(emptybutton);
                                        System.out.println("mnnfgbgf" + emptybutton);
                                    } else {  // add the integer to cell //tamsayıyı hücreye ekle
                                        sudokuCell.setValue(integerString);


                                    }
                                } else {  // if pencil button is on change pencil values
                                    sudokuCell.editPencilValues(pos);
                                }

                            }

                        }
                    });
                    // add cell to table row after all processing is done to children before moving on to next cell
                    gridLayout.addView(sudokuCell, index);
                }

                // Setting margins for cells to line them up with background image so blocks have a thicker border
                // 2dp for middle margins as cell adds 2 x 1dp border, 3dp for edges as cells add 1 x 1dp border
                ViewGroup.MarginLayoutParams cellMargin = (ViewGroup.MarginLayoutParams) gridLayout.getChildAt(index).getLayoutParams();
                if (h == 2 || h == 5) {  // add right margin for columns 3,6,9
                    cellMargin.rightMargin = margin2dp;
                }
                if (h == 8) {
                    cellMargin.rightMargin = margin3dp;
                }
                if (h == 0) {  // add left margin for column 1
                    cellMargin.leftMargin = margin3dp;
                }
                if (i == 2 || i == 5) {  // add bottom margin for rows 3,6,9
                    cellMargin.bottomMargin = margin2dp;
                }
                if (i == 8) {
                    cellMargin.bottomMargin = margin3dp;
                }
                if (i == 0) {  // add top margin for column 1
                    cellMargin.topMargin = margin3dp;
                }
            }
        }
    }

}
