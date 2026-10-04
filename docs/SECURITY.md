# V2 Security Checklist

- [ ] Authentication handled by a managed auth provider.
- [ ] No plaintext passwords.
- [ ] No service-role/admin secret in frontend code.
- [ ] RLS enabled on every user-owned table.
- [ ] Fabricator can read/write only their own permitted records.
- [ ] Admin actions use a protected server-side role.
- [ ] QR codes are one-time verifiable records.
- [ ] Points are immutable ledger transactions; balance is derived.
- [ ] Order totals are recalculated server-side.
- [ ] Reward redemption checks balance and stock server-side.
- [ ] Rate and weight-chart changes are audited.
- [ ] Input validation and rate limiting are enabled on auth/QR endpoints.
