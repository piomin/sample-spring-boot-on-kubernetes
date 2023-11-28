import http from 'k6/http';
import { check } from 'k6';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export const options = {
  duration: '60s',
  vus: 5,
  thresholds: {
    http_req_failed: ['rate<0.25'],
    http_req_duration: ['p(95)<1000'],
  },
};

export default function () {

  const payload = JSON.stringify({
      firstName: randomString(6),
      lastName: randomString(6),
      age: randomIntBetween(10, 50),
      gender: 'MALE'
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(`${__ENV.PERSONS_URI}/persons`, payload, params);

  check(res, {
    'is status 200': (res) => res.status === 200,
    'body size is > 0': (r) => r.body.length > 0,
  });
}