package org.eu.anishmukherjee.hearingaid.chat.thread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.eu.anishmukherjee.hearingaid.R;
import org.eu.anishmukherjee.hearingaid.base.BaseActivity;
import org.eu.anishmukherjee.hearingaid.model.ChatThread;
import org.eu.anishmukherjee.hearingaid.util.Utils;
import com.parse.ParseUser;

import butterknife.BindView;

/**
 * Created by Danylo Oliinyk on 11/22/17 at Virgil Security.
 * -__o
 */

public class ChatThreadActivity extends BaseActivity {

    private static final String CHAT_THREAD = "CHAT_THREAD";

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    public static void start(AppCompatActivity from, ChatThread chatThread) {
        from.startActivity(new Intent(from, ChatThreadActivity.class)
                .putExtra(CHAT_THREAD, chatThread));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_chat_thread;
    }

    @Override
    protected void postButterInit() {
        ChatThread chatThread = getIntent().getParcelableExtra(CHAT_THREAD);
        if (ParseUser.getCurrentUser().getUsername().equals(chatThread.getSenderUsername()))
            initToolbar(toolbar, chatThread.getRecipientUsername());
        else
            initToolbar(toolbar, chatThread.getSenderUsername());

        Utils.replaceFragmentNoTag(getSupportFragmentManager(),
                R.id.flContainer,
                ChatThreadFragment.newInstance(chatThread));

        showBackButton(true, view -> {
            onBackPressed();
        });
    }

    @Override public void onBackPressed() {
        super.onBackPressed();

        hideKeyboard();
    }
}
