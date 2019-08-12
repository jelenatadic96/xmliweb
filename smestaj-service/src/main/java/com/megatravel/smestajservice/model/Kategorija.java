package com.megatravel.smestajservice.model;

public enum Kategorija {
	NEKATEGORISAN,
	JEDNA_ZVEZDICA,
	DVE_ZVEZDICE,
	TRI_ZVEZDICE,
	CETIRI_ZVEZDICE,
	PET_ZVEZDICA;
	
	public static Kategorija odOcene(double ocena) {
        int celobrojno = (int) ocena;
		switch(celobrojno) {
	        case 0:
	            return NEKATEGORISAN;
	        case 1:
	            return JEDNA_ZVEZDICA;
	        case 2:
	            return DVE_ZVEZDICE;
	        case 3:
	            return TRI_ZVEZDICE;
	        case 4:
	            return CETIRI_ZVEZDICE;
	        case 5:
	            return PET_ZVEZDICA;	            
        }
        return null;
	}

	public static double odKategorije(Kategorija kategorija) {
		switch(kategorija) {
			case NEKATEGORISAN:
				return 0.0;
			case JEDNA_ZVEZDICA:
				return 1.0;
			case DVE_ZVEZDICE:
				return 2.0;
			case TRI_ZVEZDICE:
				return 3.0;
			case CETIRI_ZVEZDICE:
				return 4.0;
			case PET_ZVEZDICA:
				return 5.0;
			default:
				return 0.0;
		}
	}
	
}
