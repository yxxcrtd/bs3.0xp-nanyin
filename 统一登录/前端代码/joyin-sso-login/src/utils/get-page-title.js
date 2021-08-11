import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Joyin SSO Login'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
