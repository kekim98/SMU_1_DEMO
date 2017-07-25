package example.com.smu_1_demo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by YJJ on 2017-07-25.
 */

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener
{
    //X버튼을 만들기 위해서는 EditText자체를 커스텀화 시켜야 하기 때문에 AppCompatEditText라는 것을 상속받은 커스텀EditText를 클래스로 만들어 줍니다.

    private Drawable clearDrawable; //X버튼
    private View.OnFocusChangeListener onFocusChangeListener; //EditText로 포커스가 집중된 경우 입니다. 여기선 당연히 길이가 0보다 크면 X버튼을 출력하는 함수가 있겠죠?
    private OnTouchListener onTouchListener; //X버튼이 눌렸을때 입니다.

    public ClearEditText(final Context context) //EditText기본 생성자
    {
        super(context);
        init(); //이 함수는 X버튼의 동작과 호출을 답당합니다.
    }

    public ClearEditText(final Context context, final AttributeSet attrs)//EditText기본 생성자
    {
        super(context, attrs);
        init();
    }

    public ClearEditText(final Context context, final AttributeSet attrs, final int defStyleAttr)//EditText기본 생성자
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener)
    {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener)
    {
        this.onTouchListener = onTouchListener;
    }

    private void init()
    {
        Drawable tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear); //Drawable에 X이미지를 추가한 것을 가져옵니다.
        clearDrawable = DrawableCompat.wrap(tempDrawable);
        DrawableCompat.setTintList(clearDrawable, getHintTextColors());
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);

        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);

        addTextChangedListener(this);
    }

    @Override
    public void onFocusChange(final View view, final boolean hasFocus) //EditText에 포커스가 있을 때에만 X버튼을 보이게 하는 함수입니다.
    {
        if(hasFocus)
        {
            setClearIconVisible(getText().length() > 0);
        }
        else
        {
            setClearIconVisible(false);
        }
        if(onFocusChangeListener != null)
        {
            onFocusChangeListener.onFocusChange(view, hasFocus);
        }
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) //X버튼이 눌리는 경우 텍스트를 초기화합니다.
    {
        final int x = (int) motionEvent.getX();
        if(clearDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth())
        {
            if(motionEvent.getAction() == MotionEvent.ACTION_UP)
            {
                setError(null);
                setText(null);
            }
            return true;
        }
        if(onTouchListener != null)
        {
            return onTouchListener.onTouch(view, motionEvent);
        }
        else
        {
            return false;
        }
    }

    @Override
    public final void onTextChanged(final CharSequence s, final int start, final int before, final int count)
    {
        if(isFocused())
        {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    @Override
    public void afterTextChanged(Editable s)
    {
    }

    private void setClearIconVisible(boolean visible)
    {
        clearDrawable.setVisible(visible, false);
        setCompoundDrawables(null, null, visible ? clearDrawable : null, null);
    }
}
