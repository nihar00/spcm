package com.me.src.pojo;

public enum RecordType {
	
	General(1) { @Override public String toString() { return "General"; } },
	Respiratory(2) { @Override public String toString() { return "Respiratory"; } },
	Oral(4) { @Override public String toString() { return "Oral"; } },
	Gastritis(8) { @Override public String toString() { return "Gastritis"; } },
	Skin(16) { @Override public String toString() { return "Skin"; } },
	Orthopedic(32) { @Override public String toString() { return "Orthopedic"; } },
	Cognitive(64) { @Override public String toString() { return "Cognitive"; } },
	Neurological(128) { @Override public String toString() { return "Neurological"; } },
	EyeNoseEar(256) { @Override public String toString() { return "Eye Nose Ear"; } },
	Immunization(512){ @Override public String toString() { return "Immunization"; } },
	Allergy(1024){ @Override public String toString() { return "Allergy"; } };	
	
	private final int value;
	
    private RecordType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
