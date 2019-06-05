package interpret.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import interpret.model.*;

public class MainFrame extends JFrame
{
	/* タイトル,サイズ */
	private final String Title = "Interpret";
	private final int Width = 1200;
	private final int Height = 800;

	/* Model層 */
	private MainModel mainModel;

	/* Panel */
	private final JLabel  inputClassLabel;
	private final JTextField inputClassField;
	private final JLabel inputClassResultLabel;
	private final JButton inputClassButton;

	private final JLabel constructorLabel;
	private final JList<String> constractorList;
	private final JScrollPane constructorScrollPane;

	private final JLabel argumentLabel;
	private final JTextField argumentField;
	private final JButton argumentButton;
	private final JLabel argumentResultLabel;

	private final JLabel arrayLabel;
	private final JLabel arrayLengthLabel;
	private final JSpinner arrayLengthSpinner;
	private final JLabel arrayInitLabel;
	private final JTextField arrayInitField;
	private final JButton arrayButton;
	private final JButton arrayEmptyButton;
	private final JLabel arrayResultLabel;

	private final JLabel resultLabel;
	private final JTextArea resultArea;

	private final JLabel instanceViewLabel;
	private final JList<String> instanceViewList;
	private final JScrollPane instanceViewScrollPane;

	private final JLabel arrayViewLabel;
	private final JLabel arrayViewTargetLabel;
	private final JTextField arrayViewField;
	private final JButton arrayViewButton;
	private final JList<String> arrayViewItemList;
	private final JScrollPane arrayViewItemScrollPane;
	private final JLabel arrayViewValueLabel;
	private final JTextField arrayViewValueField;
	private final JButton arrayViewValueButton;
	private final JLabel arrayViewResultLabel;

	private final JLabel instanceInfoLabel;
	private final JLabel instanceInfoTagetLabel;
	private final JTextField instanceInfoTargetField;
	private final JButton instanceInfoTargetButton;
	private final JButton instanceInfoArrayTargetButton;
	private final JLabel instanceInfoResultLabel;

	private final JLabel instanceFieldLabel;
	private final JList<String> instanceFieldList;
	private final JScrollPane instanceFieldScrollPane;
	private final JLabel instanceFieldValueLabel;
	private final JTextField instanceFieldValueField;
	private final JButton instanceFieldValueButton;
	private final JLabel instanceFieldResultLabel;

	private final JLabel instanceMethodLabel;
	private final JList<String> instanceMethodList;
	private final JScrollPane instanceMethodScrollPane;
	private final JLabel instanceMethodArgumentLabel;
	private final JTextField instanceMethodArgumentField;
	private final JButton instanceMethodArgumentButton;
	private final JButton instanceMethodExeButton;
	private final JLabel  instanceMethodResultLabel;




	/* コンストラクタ */
	public MainFrame()
	{
		/* 基本設定  */
		super();
		this.setTitle(Title);
		this.setSize(Width, Height);
		this.getContentPane().setBackground(new Color(110, 200, 110));
		this.setLocationRelativeTo(null);	//画面中央に表示
		this.setLayout(null);	//レイアウト設定：絶対位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//「×」ボタン設定

		/* UI*/

		//---左部---

		/* 1. クラス入力部(inputClass系) */
		inputClassLabel = new JLabel("クラス名"); //ラベル
		inputClassLabel.setBounds(10, 0, 100, 20);
		this.add(inputClassLabel);

		inputClassField = new JTextField("java.lang.String");	//クラス入力フィールド
		inputClassField.setBounds(10, 20, 500, 20);
		this.add(inputClassField);

		inputClassResultLabel = new JLabel("(テスト)");	//結果ラベル+初期値
		inputClassResultLabel.setBounds(10, 40, 300, 20);
		inputClassResultLabel.setForeground(Color.RED);
		this.add(inputClassResultLabel);

		inputClassButton = new JButton("一覧取得");	//ボタン
		inputClassButton.setBounds(410, 40, 100, 30);
		inputClassButton.addActionListener(e->
		{	// ボタン処理(現在のクラス入力部フィールドのクラスのコンストラクタを取得)
			String input = this.inputClassField.getText();
			this.mainModel.getConstructorsFrom(input);
		});
		this.add(inputClassButton);

		/* 2. コンストラクタ一覧部(constructor系) */
		constructorLabel = new JLabel("コンストラクタ");	//ラベル
		constructorLabel.setBounds(10, 70, 100, 20);
		this.add(constructorLabel);

		constractorList = new JList<String>();	//コンストラクタ一覧
		constractorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//1つのみ選択
		constructorScrollPane = new JScrollPane(constractorList);
		constructorScrollPane.setBounds(10,90, 500, 200);
		this.add(constructorScrollPane);

		/* 3. 引数入力部(argument系) */
		argumentLabel = new JLabel("引数");	//ラベル
		argumentLabel.setBounds(10, 310, 50, 20);
		this.add(argumentLabel);

		argumentField = new JTextField();	//引数入力フィールド
		argumentField.setBounds(60, 310, 450, 20);
		this.add(argumentField);

		argumentResultLabel = new JLabel("(テスト)");	//結果ラベル
		argumentResultLabel.setBounds(10, 330, 300, 20);
		argumentResultLabel.setForeground(Color.RED);
		this.add(argumentResultLabel);

		argumentButton = new JButton("実行");	//実行ボタン
		argumentButton.setBounds(410, 330, 100, 30);
		argumentButton.addActionListener(e->
		{	// ボタン処理(現在のコンストラクタと引数でコンストラクタを実行)
			// リストのインデックスを取得 -> 選択中のコンストラクタを取得
			int index = this.constractorList.getSelectedIndex();
			// 引数を配列で取得 -> コンストラクタで利用する
			String args = this.argumentField.getText();
			this.mainModel.getInstanceFrom(index, args);
		});
		this.add(argumentButton);

		/* 4. 配列生成部(array系)*/
		arrayLabel = new JLabel("配列");	//ラベル
		arrayLabel.setBounds(10, 370, 200, 20);
		this.add(arrayLabel);

		arrayLengthLabel = new JLabel("長さ");	//ラベル
		arrayLengthLabel.setBounds(10, 390, 50, 20);
		this.add(arrayLengthLabel);

		arrayLengthSpinner = new JSpinner(new SpinnerNumberModel(1,1,255,1));	//255以上はnewInstanceの仕様上エラーになる
		arrayLengthSpinner.setBounds(60, 390, 450, 20);
		this.add(arrayLengthSpinner );

		arrayInitLabel = new JLabel("初期値");	//ラベル
		arrayInitLabel.setBounds(10, 420, 50, 20);
		this.add(arrayInitLabel);

		arrayInitField = new JTextField("\"aaa\",\"bbb\"");	//引数入力フィールド
		arrayInitField.setBounds(60, 420, 450, 20);
		this.add(arrayInitField);

		arrayButton = new JButton("配列生成");	//実行ボタン
		arrayButton.setBounds(410, 440, 100, 30);
		arrayButton.addActionListener(e->
		{	// クラスと長さと初期値を取得
			String input = this.inputClassField.getText();
			int length = (int)this.arrayLengthSpinner.getValue();
			String arg = this.arrayInitField.getText();
			this.mainModel.getArrayFrom(input, length, arg);
		});
		this.add(arrayButton);
		
		arrayEmptyButton = new JButton("空配列生成");	//実行ボタン
		arrayEmptyButton.setBounds(310, 440, 100, 30);
		arrayEmptyButton.addActionListener(e->
		{	// クラスと長さと初期値を取得
			String input = this.inputClassField.getText();
			int length = (int)this.arrayLengthSpinner.getValue();
			this.mainModel.getArrayFrom(input, length);
		});
		this.add(arrayEmptyButton);

		arrayResultLabel = new JLabel("(テスト)");	//結果ラベル
		arrayResultLabel.setBounds(10, 440, 300, 20);
		arrayResultLabel.setForeground(Color.RED);
		this.add(arrayResultLabel);


		/* 4. 最終結果表示部(result系) */
		resultLabel = new JLabel("結果");	//ラベル
		resultLabel.setBounds(10, 480, 100, 20);
		this.add(resultLabel);

		resultArea = new JTextArea();	//結果票火エリア
		resultArea.setBounds(10, 500, 500, 100);
		resultArea.setEditable(false);
		this.add(resultArea);

		//--- 右部 ---

		/* 5. インスタンス一覧(instanceView系)*/
		instanceViewLabel = new JLabel("インスタンス一覧");	//ラベル
		instanceViewLabel.setBounds(550, 0, 200, 20);
		this.add(instanceViewLabel);

		instanceViewList = new JList<String>();	//インスタンス一覧
		instanceViewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//1つのみ選択
		instanceViewScrollPane = new JScrollPane(instanceViewList);
		instanceViewScrollPane.setBounds(550,20, 200, 180);
		this.add(instanceViewScrollPane);

		/*6. 配列要素一覧(ArrayView系)*/
		arrayViewLabel = new JLabel("配列要素");
		arrayViewLabel.setBounds(760, 0, 200, 20);
		this.add(arrayViewLabel);

		arrayViewTargetLabel = new JLabel("対象");
		arrayViewTargetLabel.setBounds(760, 20, 200, 20);
		this.add(arrayViewTargetLabel);

		arrayViewField = new JTextField();
		arrayViewField.setBounds(800, 20, 200, 20);
		arrayViewField.setEditable(false);
		this.add(arrayViewField);

		arrayViewButton = new JButton("取得");	//実行ボタン
		arrayViewButton.setBounds(920, 40, 80, 30);
		arrayViewButton.addActionListener(e->
		{	
			// インスタンス一覧の選択中のインデックスを渡す
			int index = this.instanceViewList.getSelectedIndex();
			this.mainModel.getArrayInfoOf(index);
		});
		this.add(arrayViewButton);

		arrayViewItemList = new JList<String>();	//インスタンス一覧
		arrayViewItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//1つのみ選択
		arrayViewItemScrollPane = new JScrollPane(arrayViewItemList);
		arrayViewItemScrollPane.setBounds(800,70, 200, 50);
		this.add(arrayViewItemScrollPane);

		arrayViewValueLabel = new JLabel("値");
		arrayViewValueLabel.setBounds(760, 130, 200, 20);
		this.add(arrayViewValueLabel);

		arrayViewValueField = new JTextField();
		arrayViewValueField.setBounds(800, 130, 200, 20);
		this.add(arrayViewValueField);

		arrayViewValueButton = new JButton("変更");
		arrayViewValueButton.setBounds(920, 150, 80, 30);
		arrayViewValueButton.addActionListener(e->
		{	
			// 対象配列内の要素インデックスと修正内容をmodelに渡す
			int index = this.arrayViewItemList.getSelectedIndex();
			String input = this.arrayViewValueField.getText();
			this.mainModel.changeValueInArray(index, input);
		});
		this.add(arrayViewValueButton);

		arrayViewResultLabel = new JLabel("(テスト)");	//結果ラベル
		arrayViewResultLabel.setBounds(760, 180, 300, 20);
		arrayViewResultLabel.setForeground(Color.RED);
		this.add(arrayViewResultLabel);




		/* 7. インスタンス情報(InstanceInfo系) */
		instanceInfoLabel = new JLabel("インスタンス情報");
		instanceInfoLabel.setBounds(550, 210, 200, 20);
		this.add(instanceInfoLabel);

		instanceInfoTagetLabel = new JLabel("対象");
		instanceInfoTagetLabel.setBounds(550, 230, 200, 20);
		this.add(instanceInfoTagetLabel);

		instanceInfoTargetField = new JTextField();
		instanceInfoTargetField.setBounds(590, 230, 410, 20);
		instanceInfoTargetField.setEditable(false);
		this.add(instanceInfoTargetField);

		instanceInfoTargetButton = new JButton("取得");
		instanceInfoTargetButton.setBounds(900, 250, 100, 30);
		instanceInfoTargetButton.addActionListener(e->
		{	// 対象インスタンスを取得

		});
		this.add(instanceInfoTargetButton);

		instanceInfoArrayTargetButton = new JButton("配列取得");
		instanceInfoArrayTargetButton.setBounds(900, 280, 100, 30);
		instanceInfoArrayTargetButton.addActionListener(e->
		{	// 対象インスタンス(配列)を取得

		});
		this.add(instanceInfoArrayTargetButton);

		instanceInfoResultLabel = new JLabel("(テスト)");	//結果ラベル
		instanceInfoResultLabel .setBounds(550, 250, 300, 20);
		instanceInfoResultLabel .setForeground(Color.RED);
		this.add(instanceInfoResultLabel);


		/* 8. フィールド部(InstanceField系) */
		instanceFieldLabel = new JLabel("フィールド");
		instanceFieldLabel .setBounds(550, 320, 200, 20);
		this.add(instanceFieldLabel );

		instanceFieldList = new JList<String>();	//インスタンス一覧
		instanceFieldList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//1つのみ選択
		instanceFieldScrollPane = new JScrollPane(instanceFieldList);
		instanceFieldScrollPane.setBounds(550,340, 450, 100);
		this.add(instanceFieldScrollPane);

		instanceFieldValueLabel = new JLabel("値");
		instanceFieldValueLabel.setBounds(550, 450, 200, 20);
		this.add(instanceFieldValueLabel);

		instanceFieldValueField = new JTextField();
		instanceFieldValueField.setBounds(590, 450, 410, 20);
		this.add( instanceFieldValueField);

		instanceFieldValueButton = new JButton("変更");
		instanceFieldValueButton.setBounds(900, 470, 100, 30);
		instanceFieldValueButton.addActionListener(e->
		{	// 対象インスタンスのフィールド内容修正

		});
		this.add(instanceFieldValueButton);

		instanceFieldResultLabel = new JLabel("(テスト)");	//結果ラベル
		instanceFieldResultLabel.setBounds(550, 470, 300, 20);
		instanceFieldResultLabel.setForeground(Color.RED);
		this.add(instanceFieldResultLabel);

		/* 9. インスタンスメソッド部(InstanceMethod系) */
		instanceMethodLabel = new JLabel("メソッド");
		instanceMethodLabel.setBounds(550, 510, 200, 20);
		this.add(instanceMethodLabel);

		instanceMethodList = new JList<String>();	//インスタンス一覧
		instanceMethodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//1つのみ選択
		instanceMethodScrollPane = new JScrollPane(instanceMethodList);
		instanceMethodScrollPane.setBounds(550,530, 450, 100);
		this.add(instanceMethodScrollPane);

		instanceMethodArgumentLabel = new JLabel("引数");
		instanceMethodArgumentLabel.setBounds(550, 640, 200, 20);
		this.add(instanceMethodArgumentLabel);

		instanceMethodArgumentField = new JTextField();
		instanceMethodArgumentField.setBounds(590, 640, 410, 20);
		this.add(instanceMethodArgumentField);

		instanceMethodArgumentButton = new JButton("実行");
		instanceMethodArgumentButton.setBounds(900, 660, 100, 30);
		instanceMethodArgumentButton.addActionListener(e->
		{	// 対象インスタンスのメソッド実行

		});
		this.add(instanceMethodArgumentButton);

		instanceMethodExeButton = new JButton("実行+取得");
		instanceMethodExeButton.setBounds(900, 690, 100, 30);
		instanceMethodExeButton.addActionListener(e->
		{	// 対象インスタンスのメソッド実行+結果をインスタンスとして取得

		});
		this.add(instanceMethodExeButton);

		instanceMethodResultLabel = new JLabel("(テスト)");	//結果ラベル
		instanceMethodResultLabel.setBounds(550, 660, 300, 20);
		instanceMethodResultLabel.setForeground(Color.RED);
		this.add(instanceMethodResultLabel);

		/* 可視化 */
		this.setVisible(true);

		/* Model */
		this.mainModel = new MainModel();

		// コンストラクタ一覧取得ボタン
		this.mainModel.setInputClassEvent(e->
		{
			// エラー関係
			String error = this.mainModel.getConstractorError();
			this.inputClassResultLabel.setText(error);
			// コンストラクタのリストを表示
			ArrayList<String> list = this.mainModel.getConstractorList();
			this.constractorList.setListData(list.toArray(new String[list.size()]));
			repaint();
		});

		// 引数生成ボタン
		this.mainModel.setInstanceEvent(e->
		{
			// エラー関係
			String error = this.mainModel.getArgumentError();
			this.argumentResultLabel.setText(error);
			// 結果表示エリア更新
			String result = this.mainModel.getInstanceError();
			this.resultArea.setText(result);
			// インスタンスリスト更新
			ArrayList<String> list = this.mainModel.getInstanceList();
			this.instanceViewList.setListData(list.toArray(new String[list.size()]));
			repaint();
		});

		// 配列生成ボタン
		this.mainModel.setArrayEvent(e->
		{
			// エラー関係
			String error = this.mainModel.getArrayError();
			this.arrayResultLabel.setText(error);
			// 結果表示エリア更新
			String result = this.mainModel.getInstanceError();
			this.resultArea.setText(result);
			// インスタンスリスト更新
			ArrayList<String> list = this.mainModel.getInstanceList();
			this.instanceViewList.setListData(list.toArray(new String[list.size()]));
			repaint();
		});

		this.mainModel.setArrayInfoEvent(e->
		{
			// エラー関係
			String error = this.mainModel.getArrayInfoError();
			this.arrayViewResultLabel.setText(error);
			// 対象配列表示
			String item = this.mainModel.getArrayInfoItem();
			this.arrayViewField.setText(item);
			// 対象配列要素表示
			ArrayList<String> list = this.mainModel.getArrayInfoList();
			this.arrayViewItemList.setListData(list.toArray(new String[list.size()]));
			repaint();
		});
		// 配列の要素を修正したら
		this.mainModel.setArrayChangeEvent(e->
		{
			// エラー関係
			String error = this.mainModel.getArrayChangeError();
			this.arrayViewResultLabel.setText(error);
			// 対象配列要素(修正後)表示
			ArrayList<String> list = this.mainModel.getArrayInfoList();
			this.arrayViewItemList.setListData(list.toArray(new String[list.size()]));
			repaint();
		});

	}

}
