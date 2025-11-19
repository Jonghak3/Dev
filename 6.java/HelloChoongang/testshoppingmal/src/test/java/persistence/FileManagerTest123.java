package com.shopping.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class FileManagerTest123 {

    // 테스트에 사용할 임시 파일 이름
    private static final String TEST_FILE_NAME = "test_data.dat";

    // 테스트 전용 데이터 저장을 위한 간단한 클래스
    private static class TestObject implements Serializable {
        private static final long serialVersionUID = 1L;
        String name;
        int value;

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestObject that = (TestObject) o;
            return value == that.value && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, value);
        }
    }

    @BeforeEach
    @AfterEach
    void cleanup() {
        // 각 테스트 실행 전과 후에 테스트 파일을 삭제하여 독립성 보장
        FileManager.deleteFile(TEST_FILE_NAME);
    }

    @Test
    @DisplayName("파일 쓰기 및 읽기 테스트")
    void writeAndReadFromFile() {
        // given: 저장할 테스트 데이터 생성
        List<TestObject> originalData = new ArrayList<>();
        originalData.add(new TestObject("test1", 100));
        originalData.add(new TestObject("test2", 200));

        // when: 데이터를 파일에 쓰고 다시 읽어옴
        FileManager.writeToFile(TEST_FILE_NAME, originalData);
        List<TestObject> readData = FileManager.readFromFile(TEST_FILE_NAME);

        // then: 읽어온 데이터가 원본 데이터와 일치하는지 확인
        assertNotNull(readData, "읽어온 데이터는 null이 아니어야 합니다.");
        assertEquals(originalData.size(), readData.size(), "원본과 읽어온 데이터의 크기가 같아야 합니다.");
        assertEquals(originalData, readData, "원본과 읽어온 데이터의 내용이 같아야 합니다.");
    }

    @Test
    @DisplayName("존재하지 않는 파일 읽기 테스트")
    void readFromNonExistentFile() {
        // when: 존재하지 않는 파일을 읽으려고 시도
        List<Object> data = FileManager.readFromFile("non_existent_file.dat");

        // then: 비어 있는 리스트가 반환되어야 함
        assertNotNull(data, "결과는 null이 아니어야 합니다.");
        assertTrue(data.isEmpty(), "존재하지 않는 파일을 읽으면 빈 리스트를 반환해야 합니다.");
    }

    @Test
    @DisplayName("파일 삭제 테스트")
    void deleteFile() {
        // given: 테스트 파일을 먼저 생성
        List<String> data = List.of("delete test");
        FileManager.writeToFile(TEST_FILE_NAME, data);
        assertTrue(FileManager.fileExists(TEST_FILE_NAME), "파일이 성공적으로 생성되어야 합니다.");

        // when: 파일을 삭제
        boolean deleted = FileManager.deleteFile(TEST_FILE_NAME);

        // then: 삭제가 성공하고 파일이 더 이상 존재하지 않아야 함
        assertTrue(deleted, "파일 삭제는 성공해야 합니다.");
        assertFalse(FileManager.fileExists(TEST_FILE_NAME), "파일이 성공적으로 삭제되어야 합니다.");
    }
}
