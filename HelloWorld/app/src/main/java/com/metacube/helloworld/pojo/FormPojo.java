package com.metacube.helloworld.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class FormPojo implements Parcelable {
    private String name, mobile, gender, skills, birthDate;
    private boolean challenge;
    private float selfRating;

    public FormPojo(Parcel in) {
        name = in.readString();
        mobile = in.readString();
        gender = in.readString();
        skills = in.readString();
        birthDate = in.readString();
        challenge = in.readByte() != 0;
        selfRating = in.readFloat();
    }

    public static final Creator<FormPojo> CREATOR = new Creator<FormPojo>() {
        @Override
        public FormPojo createFromParcel(Parcel in) {
            return new FormPojo(in);
        }

        @Override
        public FormPojo[] newArray(int size) {
            return new FormPojo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(mobile);
        dest.writeString(gender);
        dest.writeString(skills);
        dest.writeString(birthDate);
        dest.writeByte((byte) (challenge ? 1 : 0));
        dest.writeFloat(selfRating);
    }
}
