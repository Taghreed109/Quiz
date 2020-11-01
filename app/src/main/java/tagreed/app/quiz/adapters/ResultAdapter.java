package tagreed.app.quiz.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import tagreed.app.quiz.R;
import tagreed.app.quiz.Results;
import tagreed.app.quiz.objects.Result;

public class ResultAdapter extends BaseAdapter {


    /* this adapter for history list view for show the history of games played before */
    ArrayList<Result> arrayList;

    public ResultAdapter(ArrayList<Result> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.result_item, parent, false);
        }

        Result result = (Result) getItem(position);

        // get the TextView and edit them
        TextView tf_date = convertView.findViewById(R.id.date_of_result);
        TextView tf_score = convertView.findViewById(R.id.score_of_result);
        RelativeLayout rl_top = convertView.findViewById(R.id.relative_top);

        tf_date.setText(result.getDate());
        tf_score.setText(result.getScore()+"");

        // make listener when user click on result to see more information about it
        rl_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), Results.class);
                intent.putExtra("TrueAnswers", result.getScore());
                intent.putExtra("answers", result.getAnswers());
                intent.putExtra("results", result.getResults());
                intent.putExtra("newdata", false);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                parent.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
