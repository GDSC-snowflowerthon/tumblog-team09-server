package snowflake.tumblog.ocr.dto;

import java.util.List;

public class OcrResponse {

    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(
        List<Image> images) {
        this.images = images;
    }

    public static class Image {

        private List<Image.Field> fields;

        public List<Image.Field> getFields() {
            return fields;
        }

        public void setFields(
            List<Image.Field> fields) {
            this.fields = fields;
        }

        public static class Field {

            private String inferText; // 추출된 텍스트

            public String getInferText() {
	return inferText;
            }
        }
    }
}
