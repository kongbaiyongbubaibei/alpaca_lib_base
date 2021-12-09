package com.alpaca.library_base.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpaca.library_base.R;


/**
 * @文件名: SearchView
 * @功能描述:
 * @Date : 2020/11/9
 * @email:
 * @修改记录:
 */
public class SearchView extends RelativeLayout {

    EditText mSearch;
    ImageView ivSearchClear;
    TextView tvSearch, mFocus;
    private onSearchClick onSearchClick;
    private boolean mCanSearch;

    public SearchView(Context context) {
        super(context);
        init();
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiAttributes(context, attrs);
        init();
    }

    private void intiAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomSearchView);
        mCanSearch = a.getBoolean(R.styleable.CustomSearchView_canSearch, false);
        a.recycle();
    }

    public SearchView.onSearchClick getOnSearchClick() {
        return onSearchClick;
    }

    public void setOnSearchClick(SearchView.onSearchClick onSearchClick) {
        this.onSearchClick = onSearchClick;
    }

    private void init() {
        View layout = View.inflate(getContext(), R.layout.view_search, this);
        mSearch = layout.findViewById(R.id.mSearch);
        mSearch.setImeActionLabel("搜索", EditorInfo.IME_ACTION_SEARCH);
        mSearch.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    if (!TextUtils.isEmpty(mSearch.getText().toString())) {
                        if (onSearchClick != null) {
                            onSearchClick.onSearch(mSearch.getText().toString().trim());
                        }
                    }
                    break;
            }
            return true;
        });
        mFocus = layout.findViewById(R.id.mFocus);
        if (mCanSearch) {
            mFocus.setVisibility(GONE);
        } else {
            mSearch.setFocusable(false);
        }
        mFocus.setOnClickListener(v -> {
            if (onSearchClick != null) {
                onSearchClick.noSearch();
            }
        });
        ivSearchClear = layout.findViewById(R.id.iv_search_clear);
        tvSearch = layout.findViewById(R.id.tv_search);

        mSearch.setOnFocusChangeListener((v, hasFocus) -> {
            if (onSearchClick != null) {
                onSearchClick.onFocus(hasFocus);
            }
            if (hasFocus) {
                tvSearch.setVisibility(View.VISIBLE);
                tvSearch.setText("取消");
            } else {
                tvSearch.setVisibility(View.INVISIBLE);
                ivSearchClear.setVisibility(View.INVISIBLE);
                mSearch.setText("");
            }
        });
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(mSearch.getText().toString())) {
                    ivSearchClear.setVisibility(View.VISIBLE);
                    tvSearch.setText("搜索");
                    ivSearchClear.setVisibility(View.VISIBLE);
                } else {
                    tvSearch.setText("取消");
                    ivSearchClear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivSearchClear.setOnClickListener(v -> mSearch.setText(""));
        tvSearch.setOnClickListener(v -> {
            if ("搜索".equals(tvSearch.getText().toString())) {
                if (onSearchClick != null) {
                    onSearchClick.onSearch(mSearch.getText().toString().trim());
                }
            } else {
                mSearch.clearFocus();
            }
        });
    }

    public interface onSearchClick {
        void onSearch(String content);

        void onFocus(boolean isFocus);

        void noSearch();

    }

    public String getSearchContent() {
        return TextUtils.isEmpty(mSearch.getText().toString()) ? "" : mSearch.getText().toString().trim();
    }

    public void setCanSearch(boolean canSearch) {
        mCanSearch = canSearch;
        mFocus.setVisibility(mCanSearch ? GONE : VISIBLE);
    }

    public void editViewRequestFocus() {
        if (mSearch != null) {
            mSearch.setFocusable(true);
            mSearch.setFocusableInTouchMode(true);
            mSearch.requestFocus();
        }
    }
}
