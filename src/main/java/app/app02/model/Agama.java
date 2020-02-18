package app.app02.model;

import java.io.Serializable;

public class Agama implements Serializable {
  private static final long serialVersionUID = -8724230319846084590L;

  public static final String TABLE_NAME = "m_agama";

  private Long id;

  private String kodeAgama;

  private String deskripsi;

  public Agama() {
  }

  public Agama(Long id, String kodeAgama, String deskripsi) {
    this.id = id;
    this.kodeAgama = kodeAgama;
    this.deskripsi = deskripsi;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getKodeAgama() {
    return kodeAgama;
  }

  public void setKodeAgama(String kodeAgama) {
    this.kodeAgama = kodeAgama;
  }

  public String getDeskripsi() {
    return deskripsi;
  }

  public void setDeskripsi(String deskripsi) {
    this.deskripsi = deskripsi;
  }

}
