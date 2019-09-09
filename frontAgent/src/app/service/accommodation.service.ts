import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { TipSmestaja } from 'app/model/global-parameters/tipSmestaja';
import { Usluga } from 'app/model/global-parameters/usluge';
import { Accommodation } from 'app/model/accommodation/accommodation';
import { AccommodationSearch } from 'app/model/accommodation/accommodationSearch';
import { AccommodationReservation } from 'app/model/accommodation/accommodationReservation';
import { Review } from 'app/model/korisnik/review';
import { Image } from 'app/model/global-parameters/image';

@Injectable()
export class AccommodationService {

  constructor(private http: HttpClient) { }

  findAllTipovi(): Observable<TipSmestaja[]> {
    return this.http.get<TipSmestaja[]>(`/rest/tipovi`);
  }

  findAllUsluge(): Observable<Usluga[]> {
    return this.http.get<Usluga[]>(`/rest/usluge`);
  }

  add(smestaj: Accommodation, idAgent: number): Observable<Accommodation[]> {
    return this.http.post<Accommodation[]>(`/rest/smestaj/agent/${idAgent}`, smestaj);
  }

  search(accommodationSearch: AccommodationSearch): Observable<Accommodation[]> {
    return this.http.post<Accommodation[]>(`rest/smestaj/pretraga`, accommodationSearch);
  }

  getAll(id: number): Observable<Accommodation[]> {
    return this.http.get<Accommodation[]>(`/rest/agenti/${id}/smestaji`);
  }

  book(accommodationReservation: AccommodationReservation): Observable<AccommodationReservation> {
    return this.http.post<AccommodationReservation>(`/rest/rezervacije`, accommodationReservation);
  }

  getUserReservations(userId: number): Observable<AccommodationReservation[]> {
    return this.http.get<AccommodationReservation[]>(`/rest/smestaj/${userId}/rezervacije`);
  }

  getReviewsForAccommodation(accommodationId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`rating/rate/room/${accommodationId}`);
  }

  getImagesForRoom(roomId: number): Observable<Image[]> {
    return this.http.get<Image[]>(`accommodationservice/reservations/room/${roomId}`);
  }

  updateResevation(reservation: AccommodationReservation): Observable<AccommodationReservation> {
    return this.http.get<AccommodationReservation>(`/rest/rezervacije/${reservation.id}`);
  }

}
