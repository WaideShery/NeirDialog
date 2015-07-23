package com.neirx.neirdialog.base;


import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.neirx.neirdialog.R;

public class RootDialogFragment extends DialogFragment {

    /**
     * Custom dialog builder
     */
    protected static class Builder {

        private final Context mContext;

        private final ViewGroup mContainer;

        private final LayoutInflater mInflater;

        private CharSequence mTitle = null;

        private CharSequence mPositiveButtonText;

        private View.OnClickListener mPositiveButtonListener;

        private CharSequence mNegativeButtonText;

        private View.OnClickListener mNegativeButtonListener;

        private CharSequence mNeutralButtonText;

        private View.OnClickListener mNeutralButtonListener;

        private CharSequence mMessage;

        private View mCustomView;

        private ListAdapter mListAdapter;

        private int mListCheckedItemIdx;

        private int mChoiceMode;

        private int[] mListCheckedItemMultipleIds;

        private AdapterView.OnItemClickListener mOnItemClickListener;

        public Builder(Context context, LayoutInflater inflater, ViewGroup container) {
            this.mContext = context;
            this.mContainer = container;
            this.mInflater = inflater;
        }

        public LayoutInflater getLayoutInflater() {
            return mInflater;
        }

        public Builder setTitle(int titleId) {
            this.mTitle = mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setPositiveButton(int textId, final View.OnClickListener listener) {
            mPositiveButtonText = mContext.getText(textId);
            mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            mPositiveButtonText = text;
            mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(int textId, final View.OnClickListener listener) {
            mNegativeButtonText = mContext.getText(textId);
            mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            mNegativeButtonText = text;
            mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(int textId, final View.OnClickListener listener) {
            mNeutralButtonText = mContext.getText(textId);
            mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, final View.OnClickListener listener) {
            mNeutralButtonText = text;
            mNeutralButtonListener = listener;
            return this;
        }

        public Builder setMessage(int messageId) {
            mMessage = mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            mMessage = message;
            return this;
        }

        public Builder setItems(ListAdapter listAdapter, int[] checkedItemIds, int choiceMode, final AdapterView.OnItemClickListener listener) {
            mListAdapter = listAdapter;
            mListCheckedItemMultipleIds = checkedItemIds;
            mOnItemClickListener = listener;
            mChoiceMode = choiceMode;
            mListCheckedItemIdx = -1;
            return this;
        }

        /**
         * Set list
         *
         * @param checkedItemIdx Item check by default, -1 if no item should be checked
         */
        public Builder setItems(ListAdapter listAdapter, int checkedItemIdx,
                                final AdapterView.OnItemClickListener listener) {
            mListAdapter = listAdapter;
            mOnItemClickListener = listener;
            mListCheckedItemIdx = checkedItemIdx;
            mChoiceMode = AbsListView.CHOICE_MODE_NONE;
            return this;
        }

        public Builder setView(View view) {
            mCustomView = view;
            return this;
        }

        public View create() {

            LinearLayout content = (LinearLayout) mInflater.inflate(R.layout.holo_dialog, mContainer, false);
            TextView vTitle = (TextView) content.findViewById(R.id.holo_title);
            TextView vMessage = (TextView) content.findViewById(R.id.sdl_message);
            FrameLayout vCustomView = (FrameLayout) content.findViewById(R.id.sdl_custom);
            Button vPositiveButton = (Button) content.findViewById(R.id.sdl_button_positive);
            Button vNegativeButton = (Button) content.findViewById(R.id.sdl_button_negative);
            Button vNeutralButton = (Button) content.findViewById(R.id.sdl_button_neutral);
            Button vPositiveButtonStacked = (Button) content.findViewById(R.id.sdl_button_positive_stacked);
            Button vNegativeButtonStacked = (Button) content.findViewById(R.id.sdl_button_negative_stacked);
            Button vNeutralButtonStacked = (Button) content.findViewById(R.id.sdl_button_neutral_stacked);
            View vButtonsDefault = content.findViewById(R.id.sdl_buttons_default);
            View vButtonsStacked = content.findViewById(R.id.sdl_buttons_stacked);
            ListView vList = (ListView) content.findViewById(R.id.sdl_list);

            Typeface regularFont = TypefaceHelper.get(mContext, "Roboto-Regular");
            Typeface mediumFont = TypefaceHelper.get(mContext, "Roboto-Medium");

            set(vTitle, mTitle, mediumFont);
            set(vMessage, mMessage, regularFont);
            setPaddingOfTitleAndMessage(vTitle, vMessage);

            if (mCustomView != null) {
                vCustomView.addView(mCustomView);
            }
            if (mListAdapter != null) {
                vList.setAdapter(mListAdapter);
                vList.setOnItemClickListener(mOnItemClickListener);
                if (mListCheckedItemIdx != -1) {
                    vList.setSelection(mListCheckedItemIdx);
                }
                if (mListCheckedItemMultipleIds != null) {
                    vList.setChoiceMode(mChoiceMode);
                    for (int i : mListCheckedItemMultipleIds) {
                        vList.setItemChecked(i, true);
                    }
                }
            }

            if (shouldStackButtons()) {
                set(vPositiveButtonStacked, mPositiveButtonText, mediumFont, mPositiveButtonListener);
                set(vNegativeButtonStacked, mNegativeButtonText, mediumFont, mNegativeButtonListener);
                set(vNeutralButtonStacked, mNeutralButtonText, mediumFont, mNeutralButtonListener);
                vButtonsDefault.setVisibility(View.GONE);
                vButtonsStacked.setVisibility(View.VISIBLE);
            } else {
                set(vPositiveButton, mPositiveButtonText, mediumFont, mPositiveButtonListener);
                set(vNegativeButton, mNegativeButtonText, mediumFont, mNegativeButtonListener);
                set(vNeutralButton, mNeutralButtonText, mediumFont, mNeutralButtonListener);
                vButtonsDefault.setVisibility(View.VISIBLE);
                vButtonsStacked.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(mPositiveButtonText) && TextUtils.isEmpty(mNegativeButtonText) && TextUtils.isEmpty
                    (mNeutralButtonText)) {
                vButtonsDefault.setVisibility(View.GONE);
            }

            return content;
        }

        /**
         * Padding is different if there is only title, only message or both.
         */
        private void setPaddingOfTitleAndMessage(TextView vTitle, TextView vMessage) {
            int grid6 = mContext.getResources().getDimensionPixelSize(R.dimen.grid_6);
            int grid4 = mContext.getResources().getDimensionPixelSize(R.dimen.grid_4);
            if (!TextUtils.isEmpty(mTitle) && !TextUtils.isEmpty(mMessage)) {
                vTitle.setPadding(grid6, grid6, grid6, grid4);
                vMessage.setPadding(grid6, 0, grid6, grid4);
            } else if (TextUtils.isEmpty(mTitle)) {
                vMessage.setPadding(grid6, grid4, grid6, grid4);
            } else if (TextUtils.isEmpty(mMessage)) {
                vTitle.setPadding(grid6, grid6, grid6, grid4);
            }
        }

        private boolean shouldStackButtons() {
            return shouldStackButton(mPositiveButtonText) || shouldStackButton(mNegativeButtonText)
                    || shouldStackButton(mNeutralButtonText);
        }

        private boolean shouldStackButton(CharSequence text) {
            final int MAX_BUTTON_CHARS = 12; // based on observation, could be done better with measuring widths
            return text != null && text.length() > MAX_BUTTON_CHARS;
        }

        private void set(Button button, CharSequence text, Typeface font, View.OnClickListener listener) {
            set(button, text, font);
            if (listener != null) {
                button.setOnClickListener(listener);
            }
        }

        private void set(TextView textView, CharSequence text, Typeface font) {
            if (text != null) {
                textView.setText(text);
                textView.setTypeface(font);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }
}
