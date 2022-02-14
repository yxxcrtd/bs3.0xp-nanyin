package com.joyintech.tams.exchange.quoteterminal.sse.file.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class TxtParser implements Iterable<String>, Closeable {
    private static final Logger logger = LoggerFactory.getLogger(TxtParser.class);

    private BufferedReader bufferedReader;
    private boolean closed;
    private List<String> contextList;
    private final RecordIterator recordIterator;

    public List<String> getContextList() {
        return contextList;
    }

    public TxtParser(String filePath) {
        this.recordIterator = new RecordIterator();
        this.contextList = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
            String str = bufferedReader.readLine();
            while (Objects.nonNull(str)) {
                contextList.add(str);
                str = bufferedReader.readLine();
            }
        } catch (Exception e) {
            logger.error("create TxtParser form dir [{}] error： {}", filePath, e);
        }
    }

    @Override
    public void close() throws IOException {
        if (Objects.isNull(this.bufferedReader)) return;
        this.bufferedReader.close();
        closed = true;
    }

    @Override
    public Iterator<String> iterator() {
        return recordIterator;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public String nextRecord() throws IOException {
        return bufferedReader.readLine();
    }

    class RecordIterator implements Iterator<String> {
        private String current;

        RecordIterator() {
        }

        private String getNextRecord() {
            try {
                return TxtParser.this.nextRecord();
            } catch (IOException var2) {
                throw new IllegalStateException(var2.getClass().getSimpleName() + " reading next record: " + var2.toString(), var2);
            }
        }

        public boolean hasNext() {
            if (TxtParser.this.isClosed()) {
                return false;
            } else {
                if (this.current == null) {
                    this.current = this.getNextRecord();
                }
                return this.current != null;
            }
        }

        public String next() {
            if (TxtParser.this.isClosed()) {
                throw new NoSuchElementException("TxtParser has been closed");
            } else {
                String next = this.current;
                this.current = null;
                if (next == null) {
                    next = this.getNextRecord();
                    if (next == null) {
                        throw new NoSuchElementException("No more records available");
                    }
                }
                return next;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }


    }
}

