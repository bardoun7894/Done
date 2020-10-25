
package com.example.done;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

        import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
        import java.util.zip.Inflater;

public class BottomSheetFilter extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_filter_sheet,container,false);
        return  v ;

    }
}
