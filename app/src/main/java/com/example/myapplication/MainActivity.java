package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {


    Button clearBtn;
    Button okBtn;
    Button sortBtn;

    Order order1 = new Order(new Corp("корпорация 1", "спб"), new Pac(1, false, "нет требований", PacType.BIG), 100, "м сокол");
    Order order2 = new Order(new Corp("корпорация 2", "м сокол"), new Pac(15, true, "нести аккуратно", PacType.BIG), 1000, "спб");
    Order order3 = new Order(new Corp("корпорация 3", "ул пушкина"), new Pac(3, false, "не смогли перевести", PacType.BIG), 35000, "сочи");
    Order[] orders = {order1, order2, order3, order3, order2, order3, order3, order2, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3, order3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        clearBtn = findViewById(R.id.clearBtn);
        okBtn = findViewById(R.id.okBtn);
        sortBtn = findViewById(R.id.sortBtn);

        SharedStorage.init(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableLayout tableLayout = findViewById(R.id.tableLay);

        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(10, 5, 10, 10);
        {
            TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setLayoutParams(tableParams);

            TextView corpName = new TextView(getApplicationContext());
            corpName.setLayoutParams(rowParams);
            corpName.setText("название комании");

            TextView pacType = new TextView(getApplicationContext());
            pacType.setText("тип посылки");
            pacType.setLayoutParams(rowParams);

            TextView pacVol = new TextView(getApplicationContext());
            pacVol.setText("размер посылки");
            pacVol.setLayoutParams(rowParams);

            TextView isFragile = new TextView(getApplicationContext());
            isFragile.setText("хрупкая");
            isFragile.setLayoutParams(rowParams);

            TextView from = new TextView(getApplicationContext());
            from.setText("откуда");
            from.setLayoutParams(rowParams);

            TextView dest = new TextView(getApplicationContext());
            dest.setText("куда");
            dest.setLayoutParams(rowParams);

            TextView cost = new TextView(getApplicationContext());
            cost.setText("цена");
            cost.setLayoutParams(rowParams);

            TextView take = new TextView(getApplicationContext());
            take.setText("принять");
            take.setLayoutParams(rowParams);


            tableRow.addView(corpName);
            tableRow.addView(pacType);
            tableRow.addView(pacVol);
            tableRow.addView(isFragile);
            tableRow.addView(from);
            tableRow.addView(dest);
            tableRow.addView(cost);
            tableRow.addView(take);
            System.out.println(take.getParent());
            tableLayout.addView(tableRow);
        }

        sortOrders();
        drawTable(tableParams, rowParams, tableLayout);

    }

    private void drawTable(TableLayout.LayoutParams tableParams, TableRow.LayoutParams rowParams, TableLayout tableLayout) {
        for (int i = 0; i < orders.length; i++) {
            TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setLayoutParams(tableParams);

            TextView corpName = new TextView(getApplicationContext());
            corpName.setLayoutParams(rowParams);
            corpName.setText(orders[i].getCorp().getName());

            TextView pacType = new TextView(getApplicationContext());
            pacType.setText(orders[i].getPac().getType().toString());
            pacType.setLayoutParams(rowParams);

            TextView pacVol = new TextView(getApplicationContext());
            pacVol.setText(String.valueOf(orders[i].getPac().getVol()));
            pacVol.setLayoutParams(rowParams);

            TextView isFragile = new TextView(getApplicationContext());
            isFragile.setText(String.valueOf(orders[i].getPac().isFragile()));
            isFragile.setLayoutParams(rowParams);

            TextView from = new TextView(getApplicationContext());
            from.setText(orders[i].getCorp().getAdr());
            from.setLayoutParams(rowParams);

            TextView dest = new TextView(getApplicationContext());
            dest.setText(orders[i].getDest());
            dest.setLayoutParams(rowParams);

            TextView cost = new TextView(getApplicationContext());
            cost.setText(String.valueOf(orders[i].getCost()));
            cost.setLayoutParams(rowParams);
            cost.setId(i * 10);

            CheckBox take = new CheckBox(getApplicationContext());
            take.setActivated(false);
            take.setLayoutParams(rowParams);
            take.setId(i * 10 + 1);


            tableRow.addView(corpName);
            tableRow.addView(pacType);
            tableRow.addView(pacVol);
            tableRow.addView(isFragile);
            tableRow.addView(from);
            tableRow.addView(dest);
            tableRow.addView(cost);
            tableRow.addView(take);
            System.out.println(take.getParent());
            tableLayout.addView(tableRow);
        }

        tableLayout.setElevation(1.0f);
    }


    public void clearEvent(View view) {
        for (int i = 0; i < orders.length; i++) {
            CheckBox checkBox = (CheckBox) findViewById(i * 10 + 1);
            checkBox.setChecked(false);
        }
    }

    public void okEvent(View view) {
        int sum = 0;
        for (int i = 0; i < orders.length; i++) {
            CheckBox checkBox = (CheckBox) findViewById(i * 10 + 1);
            if (checkBox.isChecked()) {
                TextView textView = (TextView) findViewById(i * 10);
                sum += Integer.parseInt((String) textView.getText());
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Важное сообщение!")
                .setMessage("Вы заработаете" + String.valueOf(sum) + " рублей")
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();

    }

    public void onSortBtnClick(View view){
        Intent intent = new Intent(this, SortLayoutActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void sortOrders(){
        String sortType = SharedStorage.getProperty("sort");
        switch (sortType) {
            case "from":
                Arrays.sort(orders, Comparator.comparing(Order::getCorpName));
                break;
            case "dest":
                Arrays.sort(orders, Comparator.comparing(Order::getDest));
                break;
            case "size":
                Arrays.sort(orders, Comparator.comparing(Order::getPacSize));
                break;
            case "cost":
                Arrays.sort(orders, Comparator.comparing(Order::getCost));
                break;

            default:
                break;
        }
    }


}