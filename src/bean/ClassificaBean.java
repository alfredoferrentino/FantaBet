package bean;

import java.io.Serializable;

public class ClassificaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	int posizione;
	String nickname;
	int punteggio;
	
	

	public ClassificaBean() {
		posizione=0;
		nickname="";
		punteggio=0;
	}



	public int getPosizione() {
		return posizione;
	}



	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getPunteggio() {
		return punteggio;
	}



	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}



	@Override
	public String toString() {
		return "ClassificaBean [posizione=" + posizione + ", nickname=" + nickname + ", punteggio=" + punteggio + "]";
	}
	
	
	

	

	


}


