package test2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        double start=System.currentTimeMillis();
        String[] formats = {"_c=%d___d=%d_", "_c=%d__(d=%d)", "(c=%d)__d=%d_", "(c=%d)_(d=%d)"};

        // �����͸� ������ �� �ʱ�ȭ
        ConcurrentHashMap<Integer, Integer> numberFrequency = new ConcurrentHashMap<>();

        // ���ķ� ���� �о����
        CompletableFuture<Void>[] futures = new CompletableFuture[2500];
        for (int i = 0; i < 2500; i++) {
            int c = i / 50 + 1;
            int d = i % 50 + 1;
            futures[i] = CompletableFuture.supplyAsync(() -> {
                for(int j=0;j<4;j++) {
                    String urlString="file:///D:/Archive/file " + String.format(formats[j], c, d) + ".txt";
                    try {
                        URL url = new URL(urlString);
                        InputStream in =(InputStream)url.getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] numbers = line.trim().split(" ");
                            for (String num : numbers) {
                                int intNum = Integer.parseInt(num);
                                numberFrequency.merge(intNum, 1, Integer::sum);
                            }
                        }
                        reader.close();

                    } catch (IOException e) {
                        // ������ �������� �ʴ� ��� ����
                        continue;
                    }
                    break;
                }
                return null;
            });
        }

        // ��� CompletableFuture�� �Ϸ�� ������ ���
        CompletableFuture.allOf(futures).get();

        // ��� ���
        int maxFreqNum = 0, maxFreq = 0;
        int minFreqNum = 0, minFreq = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE, minNum = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : numberFrequency.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            // �ֺ� ���
            if (freq > maxFreq) {
                maxFreqNum = num;
                maxFreq = freq;
            }

            // �ּ� �󵵰� ���
            if (freq < minFreq) {
                minFreqNum = num;
                minFreq = freq;
            }

            // �ִ밪 �� �ּҰ� ���
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);
        }
        double end=System.currentTimeMillis();
        // ��� ���
        System.out.println("max freq num: " + maxFreqNum + ", max freq: " + maxFreq);
        System.out.println("min freq num: " + minFreqNum + ", min freq: " + minFreq);
        System.out.println("max: " + maxNum + ", min: " + minNum);
        System.out.println("Run time is "+(end-start)+"ms");

    }
}
