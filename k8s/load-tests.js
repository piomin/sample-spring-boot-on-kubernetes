import http from 'k6/http';
import { check } from 'k6';

export default function () {

  const payload = JSON.stringify({
      firstName: 'aaa',
      lastName: 'bbb',
      age: 50,
      gender: 'MALE'
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(`http://localhost:8080/persons`, payload, params);

  check(res, {
    'is status 200': (res) => res.status === 200,
    'body size is > 0': (r) => r.body.length > 0,
  });
}