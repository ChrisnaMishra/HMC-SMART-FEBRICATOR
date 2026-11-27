# HMC Smart Fabricator

**Hulas Smart Fabricator** is a free mobile web app for fabricators who work with Hulas Metal Crafts Ltd. stainless steel pipes and tubes (Biratnagar, Nepal).

Live app: https://chrisnamishra.github.io/HMC-SMART-FEBRICATOR/

## What fabricators can do

- **Calculate:** pipe weight, price and VAT from the official Hulas weight chart
- **Estimate:** material for projects such as railings, with wastage
- **Plan cutting:** enter cut lengths and get a cutting plan for 6 m pipes
- **Order:** build a material list and send it to Hulas on WhatsApp
- **Earn:** scan the QR on Hulas pipes (1 kg = 1 point) and redeem rewards
- **Save projects** and see scan and order history

Products: round, square and rectangle pipes in 202 and 304 grade.

## Install on a phone

Open the live link, then use **Add to Home screen** (Android or iPhone). The app works like a normal app and can open offline.

## Project files

| Path | Purpose |
|---|---|
| `index.html` | The whole app (screens, styles and logic) |
| `manifest.json`, `sw.js` | Installable app and offline support |
| `assets/`, `*.webp` | Product, logo and banner images |
| `backend/` | Planned V2 backend (Supabase / PostgreSQL schema) |
| `docs/SECURITY.md` | V2 security checklist |

## Status

The live app is a browser-only prototype: accounts and points are stored on the user's device. Hulas verifies weight and rate before confirming any order. See `backend/README.md` for the V2 plan.

## Author

Krishna Mishra, Marketing & Sales Manager, Hulas Metal Crafts Ltd.
