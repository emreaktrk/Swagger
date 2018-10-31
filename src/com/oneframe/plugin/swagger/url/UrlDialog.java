package com.oneframe.plugin.swagger.url;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiClass;
import com.oneframe.plugin.swagger.Swagger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UrlDialog extends DialogWrapper {

    private JTextField mUrl = new JTextField(20);

    UrlDialog(PsiClass psi) {
        super(psi.getProject());

        init();
        setTitle("Write Swagger Url");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel label = new JLabel("Enter your swagger url");
        panel.add(label, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(mUrl, constraints);

        return panel;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();

        try {
            String url = mUrl.getText();
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream content = response.getEntity().getContent();
                InputStreamReader input = new InputStreamReader(content);
                BufferedReader reader = new BufferedReader(input);
                String result = reader.readLine();

                System.out.println(result);

                Swagger swagger = new Gson().fromJson(result, Swagger.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
