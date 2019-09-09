import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Accommodation } from 'app/model/accommodation/accommodation';
import { AccommodationSearch } from 'app/model/accommodation/accommodationSearch';
import { AccommodationReservation } from 'app/model/accommodation/accommodationReservation';
import { Image } from 'app/model/global-parameters/image';
import { Review } from 'app/model/korisnik/review';

@Injectable()
export class AccommodationService {

  baseUrl: string = environment.baseUrl + '/smestaj-service/rest/smestaj';

  constructor(private http: HttpClient) { }

  search(accommodationSearch: AccommodationSearch): Observable<Accommodation[]> {
    return this.http.post<Accommodation[]>(this.baseUrl + `smestaj-service/rest/smestaj`, accommodationSearch);
  }

  getAll(): Observable<Accommodation[]> {
    return this.http.get<Accommodation[]>(this.baseUrl);
  }

  getOne(accommodationId: number): Observable<Accommodation> {
    return this.http.get<Accommodation>(this.baseUrl + `accommodationservice/hotels/1/rooms/${accommodationId}`);
  }

  book(accommodationReservation: AccommodationReservation, userId: number): Observable<AccommodationReservation> {
    return this.http.post<AccommodationReservation>(this.baseUrl + `accommodationservice/hotels/1/rooms/${accommodationReservation.smestajnaJedinicaDTO.id}/reservations/${userId}`, accommodationReservation);
  }

  getUserReservations(userId: number): Observable<AccommodationReservation[]> {
    return this.http.get<AccommodationReservation[]>(this.baseUrl + `accommodationservice/reservations/user/${userId}`);
  }

  getReviewsForAccommodation(accommodationId: number): Observable<Review[]> {
    return this.http.get<Review[]>(this.baseUrl + `rating/rate/room/${accommodationId}`);
  }

  cancel(accommodationId: number): Observable<boolean> {
    return this.http.delete<boolean>(this.baseUrl + `accommodationservice/reservations/${accommodationId}`);
  }

  updateRating(accommodationId: number): Observable<boolean>{
    return this.http.get<boolean>(this.baseUrl + `accommodationservice/hotels/1/rooms/updateRoom/${accommodationId}`);
  }

  getImagesForRoom(roomId: number): Observable<Image[]> {
    return this.http.get<Image[]>(this.baseUrl + `accommodationservice/reservations/room/${roomId}`);
  }

}
