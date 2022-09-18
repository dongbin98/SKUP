package com.dbsh.skup.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbsh.skup.R;

import java.util.List;

public class AttendanceDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<AttendanceDetailItem> data;

    Context context;

    AttendanceDetailAdapter(List<AttendanceDetailItem> data) {this.data = data;}
    public void dataClear() {data.clear();}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.attendance_detail_list, parent, false);
        return new AttendanceDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final AttendanceDetailItem item = data.get(position);
        final AttendanceDetailHolder itemController = (AttendanceDetailHolder) holder;

        if(item.text.equals("출석")) {
            itemController.attendanceType.setTextColor(context.getColor(R.color.mainBlue));
            itemController.attendanceState.setTextColor(context.getColor(R.color.mainBlue));
            itemController.attendanceTypeCircle.setImageDrawable(context.getDrawable(R.drawable.imageview_attendance_blue_dot_circle));
        } else if(item.text.equals("지각")) {
            itemController.attendanceType.setTextColor(context.getColor(R.color.mainYellow));
            itemController.attendanceState.setTextColor(context.getColor(R.color.mainYellow));
            itemController.attendanceTypeCircle.setImageDrawable(context.getDrawable(R.drawable.imageview_attendance_yellow_dot_circle));
        } else {
            itemController.attendanceType.setTextColor(context.getColor(R.color.mainRed));
            itemController.attendanceState.setTextColor(context.getColor(R.color.mainRed));
            itemController.attendanceTypeCircle.setImageDrawable(context.getDrawable(R.drawable.imageview_attendance_red_dot_circle));
        }

        itemController.attendanceType.setText(item.text);
        itemController.attendanceState.setText(item.text2);
        itemController.attendanceDate.setText("ㆍ" + item.text3);
        itemController.attendanceString.setText(item.text4);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AttendanceDetailHolder extends RecyclerView.ViewHolder {
        TextView attendanceType;
        TextView attendanceState;
        TextView attendanceDate;
        TextView attendanceString;
        ImageView attendanceTypeCircle;

        public AttendanceDetailHolder(View itemView) {
            super(itemView);
            attendanceType = (TextView) itemView.findViewById(R.id.attendance_type);
            attendanceState = (TextView) itemView.findViewById(R.id.attendance_state);
            attendanceDate = (TextView) itemView.findViewById(R.id.attendance_date);
            attendanceString = (TextView) itemView.findViewById(R.id.attendance_string);
            attendanceTypeCircle = (ImageView) itemView.findViewById(R.id.attendance_type_circle);
        }
    }

    public static class AttendanceDetailItem {
        public String text, text2, text3, text4;
        public AttendanceDetailItem() {}
        public AttendanceDetailItem(String text, String text2, String text3, String text4) {
            this.text = text;       // 동그라미 안에 출석 타입
            this.text2 = text2;     // 출석 상태
            this.text3 = text3;     // 날짜
            this.text4 = text4;     // 텍스트로 안내
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText2() {
            return text2;
        }

        public void setText2(String text2) {
            this.text2 = text2;
        }

        public String getText3() {
            return text3;
        }

        public void setText3(String text3) {
            this.text3 = text3;
        }

        public String getText4() {
            return text4;
        }

        public void setText4(String text4) {
            this.text4 = text4;
        }
    }
}